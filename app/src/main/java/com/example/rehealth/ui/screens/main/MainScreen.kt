package com.example.rehealth.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rehealth.navigation.main.MainNavGraph
import com.example.rehealth.ui.screens.main.bottombar.CustomBottomBar
import com.example.rehealth.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(sharedViewModel: SharedViewModel) {

    val navHostController = rememberNavController()




    Scaffold(
        bottomBar = {
            CustomBottomBar(navHostController = navHostController)
        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            MainNavGraph(navHostController = navHostController, sharedViewModel = sharedViewModel)
        }

    }
}




