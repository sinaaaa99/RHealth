package com.example.rehealth.data.broadcast

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.rehealth.MainActivity
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.models.QuizReminder
import com.example.rehealth.data.prepopulate.QuizAlarmScheduler
import com.example.rehealth.navigation.routes.Routes.Message
import com.example.rehealth.navigation.routes.Routes.QuizNotification
import java.time.LocalDateTime

class QuizAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val id = intent.getIntExtra("id", 1)
        val name = intent.getStringExtra("name")

        //new
        val quizReminderId =
            intent.getIntExtra("quizId", 0)


        val timeReminder: LocalDateTime?
        timeReminder = intent.getSerializableExtra("timeReminder") as LocalDateTime

        context.let { ctx ->

            //open activity from Notification
            val activityIntent = Intent(
                Intent.ACTION_VIEW,
                "$QuizNotification/$Message=$id".toUri(),
                ctx,
                MainActivity::class.java
            )
            val pendingActivityIntent: PendingIntent = TaskStackBuilder.create(ctx).run {

                addNextIntentWithParentStack(activityIntent)
                getPendingIntent(
                    id,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            }


            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val drugImage = BitmapFactory.decodeResource(
                ctx.resources,
                R.drawable.pic_quiz
            )

            val builder = NotificationCompat.Builder(ctx, "Drug")
                .setSmallIcon(R.drawable.ic_bell_hand)
                .setLargeIcon(drugImage)
                .setContentTitle(name)
                .setContentText("زمان پاسخگویی به پرسشنامه ها")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingActivityIntent)

            notificationManager.notify(id, builder.build())


            //repeat alarm
            val quizScheduler: QuizScheduler = QuizAlarmScheduler(context)

            /*val repeatTime =
                timeReminder.plusDays(1).atZone(ZoneId.systemDefault()).toEpochSecond()
                    .times(1000)*/

            val quizReminder: QuizReminder?

            quizReminder = QuizReminder(
                quizReminderId,
                id,
                name ?: "",
                timeReminder.plusDays(1),
            )

            quizScheduler.repeat(quizReminder)


        }
    }
}