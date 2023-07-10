package com.example.rehealth.navigation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rehealth.ui.screens.main.home.HomeScreen
import com.example.rehealth.ui.screens.main.medicine.MedicineScreen
import com.example.rehealth.ui.screens.main.setting.SettingScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

@Composable
fun MainNavGraph(navHostController: NavHostController,sharedViewModel: SharedViewModel) {

    NavHost(navController = navHostController, startDestination = ScreensNavItem.Home.route) {

        composable(ScreensNavItem.Setting.route) {

            SettingScreen()
        }
        composable(ScreensNavItem.Home.route) {

            HomeScreen()
        }
        composable(ScreensNavItem.Medicine.route) {

            MedicineScreen(sharedViewModel)
        }

    }
}