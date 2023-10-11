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
import com.example.rehealth.navigation.routes.Routes.Message
import com.example.rehealth.navigation.routes.Routes.TestNotification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {


        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("alarmId", 0)


        context.let { ctx ->

            //open activity from Notification
            val activityIntent =
                Intent(
                    Intent.ACTION_VIEW,
                    "$TestNotification/$Message=$id".toUri(),
                    ctx,
                    MainActivity::class.java
                )
            val activityPendingIntent: PendingIntent = TaskStackBuilder.create(ctx).run {
                addNextIntentWithParentStack(activityIntent)
                getPendingIntent(
                    id,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

            val notificationManger =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val testImage = BitmapFactory.decodeResource(
                ctx.resources,
                R.drawable.pic_test_set
            )

            val builder =
                NotificationCompat.Builder(ctx, "TestsId")
                    .setSmallIcon(R.drawable.ic_bell_hand)
                    .setLargeIcon(testImage)
                    .setContentTitle(name)
                    .setContentText("یادآور آزمایش $name")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(activityPendingIntent)


            notificationManger.notify(id, builder.build())

        }
    }
}