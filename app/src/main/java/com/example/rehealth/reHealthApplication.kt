package com.example.rehealth

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.rehealth.util.Constants.CHANNEL_ID
import com.example.rehealth.util.Constants.CHANNEL_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ReHealthApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel1 = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val channel2 = NotificationChannel(
            "Drug",
            "Drug",
            NotificationManager.IMPORTANCE_HIGH
        )


        val channels = mutableListOf(channel1,channel2)
//        notificationManager.createNotificationChannel(channel)
        notificationManager.createNotificationChannels(channels)
    }
}