package com.example.rehealth.ui.screens.setting.quiz

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.models.QuizReminder
import com.example.rehealth.ui.screens.setting.drugs.FloatingActionButton
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListQuizAlarmScreen(
    sharedViewModel: SharedViewModel,
    quizScheduler: QuizScheduler,
    navHostController: NavHostController
) {

    val allQuizReminder by sharedViewModel.allQuizReminder.collectAsState()
    val quizReminderId by sharedViewModel.quizReminderId
//    var quizAlarmId by sharedViewModel.quizAlarmId

    LaunchedEffect(key1 = quizReminderId ){

        sharedViewModel.getAllQuizReminder()
    }

    Scaffold(floatingActionButton = {

        FloatingActionButton{
            navHostController.navigate("AddQuizReminderScreen")
        }
    }) {

        LazyColumn(modifier = Modifier.padding(it)) {

            if (allQuizReminder is RequestState.Success) {

                itemsIndexed(
                    (allQuizReminder as
                            RequestState.Success<List<QuizReminder>>).data
                ) { _, data ->


                    QuizReminderItem(data.name) {

                        sharedViewModel.quizReminderId.value = data.quizId
                        sharedViewModel.quizAlarmId.value = data.alarmId


                        quizScheduler.cancel(data)

                        sharedViewModel.deleteQuizReminder()

                    }


                }
            }
        }


    }
}