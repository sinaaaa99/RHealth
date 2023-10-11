package com.example.rehealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.data.prepopulate.VisitScheduleImpl
import com.example.rehealth.data.prepopulate.DrugAlamScheduler
import com.example.rehealth.data.prepopulate.QuizAlarmScheduler
import com.example.rehealth.data.prepopulate.TestAlarmScheduler
import com.example.rehealth.ui.screens.main.MainScreen
import com.example.rehealth.ui.theme.ReHealthTheme
import com.example.rehealth.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val visitScheduler: VisitScheduler = VisitScheduleImpl(this)
        val drugScheduler: DrugScheduler = DrugAlamScheduler(this)
        val testScheduler: TestScheduler = TestAlarmScheduler(this)
        val quizScheduler: QuizScheduler = QuizAlarmScheduler(this)

        setContent {
            ReHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        sharedViewModel,
                        visitScheduler,
                        drugScheduler,
                        testScheduler,
                        quizScheduler
                    )
                }
            }
        }
    }
}