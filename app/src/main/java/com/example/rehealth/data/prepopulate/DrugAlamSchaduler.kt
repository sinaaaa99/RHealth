package com.example.rehealth.data.prepopulate

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.rehealth.data.broadcast.DrugAlarmReceiver
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.DrugReminder
import java.time.ZoneId

class DrugAlamScheduler(
    private val context: Context
) : DrugScheduler {

    private val alarmManager: AlarmManager = context.getSystemService(AlarmManager::class.java)
    override fun schedule(drugReminder: DrugReminder) {

        val intent = Intent(context, DrugAlarmReceiver::class.java).apply {
            putExtra("id", drugReminder.id)
            putExtra("name", drugReminder.name)
            putExtra("dooz", drugReminder.dooz)
            putExtra("reminder1", drugReminder.reminder1)
            /*putExtra("reminder2", drugReminder.reminder2)
            putExtra("reminder3", drugReminder.reminder3)
            putExtra("reminder4", drugReminder.reminder4)*/
        }

        val alarmTime1 = drugReminder.reminder1.atZone(ZoneId.systemDefault())?.toEpochSecond()
            ?.times(1000)

        /*val alarmTime2 = drugReminder.reminder2?.atZone(ZoneId.systemDefault())?.toEpochSecond()
            ?.times(1000)

        val alarmTime3 = drugReminder.reminder3?.atZone(ZoneId.systemDefault())?.toEpochSecond()
            ?.times(1000)

        val alarmTime4 = drugReminder.reminder4?.atZone(ZoneId.systemDefault())?.toEpochSecond()
            ?.times(1000)*/

        if (alarmTime1 != null) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmTime1,
                600000,
                PendingIntent.getBroadcast(
                    context,
                    drugReminder.id,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE

                )
            )
        }
    }

    override fun cancel(drugReminder: DrugReminder) {

    }

    override fun update(drugReminder: DrugReminder) {

    }

}