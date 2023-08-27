package com.example.rehealth.data.prepopulate

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.rehealth.data.broadcast.TestAlarmReceiver
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.data.models.TestReminder
import java.time.ZoneId

class TestAlarmScheduler(private val context: Context) : TestScheduler {

    private val alarmManager: AlarmManager = context.getSystemService(AlarmManager::class.java)

    override fun schedule(testReminder: TestReminder) {

        val intent = Intent(context, TestAlarmReceiver::class.java).apply {
            putExtra("name", testReminder.name)
            putExtra("alarmId", testReminder.alarmId)
        }

        Log.d("notificationsina",testReminder.name)
        Log.d("notificationsina",testReminder.time.toString())
        Log.d("notificationsina",testReminder.alarmId.toString())

        val alarmTime = testReminder.time.atZone(ZoneId.systemDefault()).toEpochSecond().times(1000)

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                testReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )



    }

    override fun cancel(testReminder: TestReminder) {

        Log.d("alarmIDTEst",testReminder.alarmId.toString())

        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                testReminder.alarmId,
                Intent(context, TestAlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}