package com.example.rehealth.data.broadcast

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.rehealth.R
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

            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle(title)
                .setContentText("یادآور ویزیت $title")
                .setPriority(NotificationCompat.PRIORITY_HIGH)


            notificationManager.notify(id, builder.build())


        }

    }
}