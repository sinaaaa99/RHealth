package com.example.rehealth.data.prepopulate

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.rehealth.data.interfaces.AlarmScheduler
import com.example.rehealth.data.broadcast.AlarmReceiver
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.util.Constants.NOTIF_ID
import com.example.rehealth.util.Constants.NOTIF_TITLE
import java.time.ZoneId

class AlarmScheduleImpl(
    private val context: Context
) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @SuppressLint("ScheduleExactAlarm")
    override fun schedule(visitReminder: VisitReminder) {

        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(NOTIF_ID, visitReminder.id)
            putExtra(NOTIF_TITLE, visitReminder.title)
            putExtra("", visitReminder.description)
        }

        val alarmTime = visitReminder.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                visitReminder.id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )

    }

    override fun cancel(visitReminder: VisitReminder) {

    }
}