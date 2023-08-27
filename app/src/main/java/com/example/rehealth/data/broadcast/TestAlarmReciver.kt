package com.example.rehealth.data.broadcast

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.rehealth.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {


        val name = intent.getStringExtra("name")
        val id = intent.getIntExtra("alarmId", 0)


        context.let { ctx ->

            val notificationManger =
                ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val builder =
                NotificationCompat.Builder(ctx, "TestsId")
                    .setSmallIcon(R.drawable.ic_home)
                    .setContentTitle(name)
                    .setContentText("یادآور آزمایش $name")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)

            notificationManger.notify(id, builder.build())

        }
    }
}