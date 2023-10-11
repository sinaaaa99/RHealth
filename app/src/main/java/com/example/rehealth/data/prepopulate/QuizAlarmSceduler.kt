package com.example.rehealth.data.prepopulate

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.rehealth.data.broadcast.QuizAlarmReceiver
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.models.QuizReminder
import java.time.ZoneId

class QuizAlarmScheduler(private val context: Context) : QuizScheduler {

    private val alarmManager: AlarmManager = context.getSystemService(AlarmManager::class.java)
    override fun schedule(quizReminder: QuizReminder) {

        val intent = Intent(context, QuizAlarmReceiver::class.java).apply {
            putExtra("id", quizReminder.alarmId)
            putExtra("name", quizReminder.name)
        }

        val alarmTime = quizReminder.reminder.atZone(ZoneId.systemDefault()).toEpochSecond()
            .times(1000)


        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            AlarmManager.INTERVAL_DAY,
            PendingIntent.getBroadcast(
                context,
                quizReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )

    }

    override fun cancel(quizReminder: QuizReminder) {

        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                quizReminder.alarmId,
                Intent(context, QuizAlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )

        )

    }
}