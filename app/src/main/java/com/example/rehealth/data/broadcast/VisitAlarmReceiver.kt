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
import com.example.rehealth.navigation.routes.Routes.VisitNotification
import com.example.rehealth.util.Constants.CHANNEL_ID
import com.example.rehealth.util.Constants.NOTIF_ID
import com.example.rehealth.util.Constants.NOTIF_TITLE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VisitAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val title = intent.getStringExtra(NOTIF_TITLE)
        val id = intent.getIntExtra(NOTIF_ID, (1..9999).random())

        context.let { ctx ->

            //open activity from Notification
            val activityIntent = Intent(
                Intent.ACTION_VIEW,
                "$VisitNotification/$Message=$id".toUri(),
                ctx,
                MainActivity::class.java
            )
            val pendingActivityIntent: PendingIntent = TaskStackBuilder.create(ctx).run {
                addNextIntentWithParentStack(activityIntent)
                getPendingIntent(
                    id,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            }

            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val visitImage = BitmapFactory.decodeResource(
                ctx.resources,
                R.drawable.pic_visit
            )

            val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_bell_hand)
                .setLargeIcon(visitImage)
                .setContentTitle(title)
                .setContentText("یادآور ویزیت $title")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingActivityIntent)


            notificationManager.notify(id, builder.build())


        }

    }
}