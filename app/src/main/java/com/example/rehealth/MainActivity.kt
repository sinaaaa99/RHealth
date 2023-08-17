package com.example.rehealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rehealth.data.interfaces.AlarmScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.prepopulate.AlarmScheduleImpl
import com.example.rehealth.data.prepopulate.DrugAlamScheduler
import com.example.rehealth.ui.screens.main.MainScreen
import com.example.rehealth.ui.theme.ReHealthTheme
import com.example.rehealth.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val alarmSchedule: AlarmScheduler = AlarmScheduleImpl(this)
        val drugScheduler: DrugScheduler = DrugAlamScheduler(this)

        setContent {
            ReHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(sharedViewModel, alarmSchedule, drugScheduler)
                }
            }
        }
    }
}