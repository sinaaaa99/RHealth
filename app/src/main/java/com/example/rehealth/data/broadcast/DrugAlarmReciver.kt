package com.example.rehealth.data.broadcast

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.rehealth.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrugAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val id = intent.getIntExtra("id", 1)
        val name = intent.getStringExtra("name")
        val dooz = intent.getIntExtra("dooz", 100)
        val reminder = intent.getLongExtra("reminder1", 1)

        context.let { ctx ->

            val notificationManager =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder = NotificationCompat.Builder(ctx, "Drug")
                .setSmallIcon(R.drawable.ic_home)
                .setContentTitle(name)
                .setContentText(dooz.toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)

            notificationManager.notify(id, builder.build())


        }
    }
}