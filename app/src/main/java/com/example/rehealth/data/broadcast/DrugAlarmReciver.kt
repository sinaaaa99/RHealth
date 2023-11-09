package com.example.rehealth.data.broadcast

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.rehealth.MainActivity
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.data.prepopulate.DrugAlamScheduler
import com.example.rehealth.navigation.routes.Routes.DrugAlarmId
import com.example.rehealth.navigation.routes.Routes.DrugNotification
import com.example.rehealth.navigation.routes.Routes.DrugShiftCode
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.util.UUID

@AndroidEntryPoint
class DrugAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val id = intent.getIntExtra("id", 1)
        val name = intent.getStringExtra("name")
        val shiftCode = intent.getIntExtra("shiftCode", 0)

        //new
        val drugReminderId: UUID?
        drugReminderId = intent.getSerializableExtra("drugId") as UUID
//        val drugId = UUID.fromString(drugIdString)

        Log.d("drugIdString", drugReminderId.toString())

        val timeReminder: LocalDateTime?
//        if (intent.hasExtra("timeReminder"))
        timeReminder = intent.getSerializableExtra("timeReminder") as LocalDateTime


        Log.d("drugIdString", timeReminder.toString())

        context.let { ctx ->

            //open activity from Notification
            val activityIntent = Intent(
                Intent.ACTION_VIEW,
                "$DrugNotification/$DrugAlarmId=$id/$DrugShiftCode=$shiftCode".toUri(),
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
                R.drawable.pic_drug_set
            )

            val builder = NotificationCompat.Builder(ctx, "Drug")
                .setSmallIcon(R.drawable.ic_bell_hand)
                .setLargeIcon(drugImage)
                .setContentTitle(name)
                .setContentText("زمان مصرف داروهای $name")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingActivityIntent)

            notificationManager.notify(id, builder.build())


            //repeat alarm
            val drugScheduler: DrugScheduler = DrugAlamScheduler(context)

            /*val repeatTime =
                timeReminder.plusDays(1).atZone(ZoneId.systemDefault()).toEpochSecond()
                    .times(1000)*/

            val drugReminder: DrugReminder?

            drugReminder = DrugReminder(
                drugReminderId,
                id,
                name ?: "",
                timeReminder.plusDays(1),
                shiftCode, 0
            )

            drugScheduler.repeat(drugReminder)


        }
    }
}