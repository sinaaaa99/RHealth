package com.example.rehealth.data.prepopulate

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.rehealth.data.broadcast.DrugAlarmReceiver
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.drug.DrugReminder
import java.time.ZoneId

class DrugAlamScheduler(
    private val context: Context
) : DrugScheduler {

    private val alarmManager: AlarmManager = context.getSystemService(AlarmManager::class.java)
    override fun schedule(drugReminder: DrugReminder) {

        val intent = Intent(context, DrugAlarmReceiver::class.java).apply {
            putExtra("id", drugReminder.alarmId)
            putExtra("name", drugReminder.name)
            putExtra("shiftCode", drugReminder.shiftCode)

            //new
            putExtra("drugId",drugReminder.reminderId)
            putExtra("timeReminder",drugReminder.reminder)
        }

        val alarmTime = drugReminder.reminder.atZone(ZoneId.systemDefault()).toEpochSecond()
            .times(1000)


        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                drugReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )
        /*alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            AlarmManager.INTERVAL_DAY,
            PendingIntent.getBroadcast(
                context,
                drugReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )*/


    }

    override fun cancel(drugReminder: DrugReminder) {

        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                drugReminder.alarmId,
                Intent(context, DrugAlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )

        )

    }

    override fun repeat(drugReminder: DrugReminder) {

        val intent = Intent(context, DrugAlarmReceiver::class.java).apply {
            putExtra("id", drugReminder.alarmId)
            putExtra("name", drugReminder.name)
            putExtra("shiftCode", drugReminder.shiftCode)

            //new
            putExtra("drugId",drugReminder.reminderId)
            putExtra("timeReminder",drugReminder.reminder)
        }

        Log.d("repeatDrugAlarm",drugReminder.reminder.toString())


        val alarmTime = drugReminder.reminder.atZone(ZoneId.systemDefault()).toEpochSecond()
            .times(1000)


        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            PendingIntent.getBroadcast(
                context,
                drugReminder.alarmId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

            )
        )
    }

}