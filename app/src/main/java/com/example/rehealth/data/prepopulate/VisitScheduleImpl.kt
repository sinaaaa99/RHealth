package com.example.rehealth.data.prepopulate

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.broadcast.AlarmReceiver
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.util.Constants.NOTIF_ID
import com.example.rehealth.util.Constants.NOTIF_TITLE
import java.time.ZoneId

class VisitScheduleImpl(
    private val context: Context
) : VisitScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(visitReminder: VisitReminder) {

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(NOTIF_ID, visitReminder.alarmId)
            putExtra(NOTIF_TITLE, visitReminder.title)
        }

        Log.d("visitAlaarmTime",visitReminder.time.toString())

        val alarmTime = visitReminder.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000


        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                visitReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

    }

    override fun cancel(visitReminder: VisitReminder) {

        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                visitReminder.alarmId,
                Intent(context,AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}