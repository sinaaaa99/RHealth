package com.example.rehealth.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.navigation.main.MainNavGraph
import com.example.rehealth.ui.screens.main.bottombar.CustomBottomBar
import com.example.rehealth.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    sharedViewModel: SharedViewModel,
    visitScheduler: VisitScheduler,
    drugScheduler: DrugScheduler,
    testScheduler: TestScheduler,
    quizScheduler: QuizScheduler
) {

    val navHostController = rememberNavController()




    Scaffold(
        bottomBar = {
            CustomBottomBar(navHostController)
        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            MainNavGraph(
                navHostController,
                sharedViewModel,
                visitScheduler,
                drugScheduler,
                testScheduler,
                quizScheduler
            )
        }

    }
}




