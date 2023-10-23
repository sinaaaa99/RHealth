package com.example.rehealth.navigation.bottombar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.ui.screens.main.home.HomeScreen
import com.example.rehealth.ui.screens.main.medicine.MedicineScreen
import com.example.rehealth.ui.screens.main.setting.SettingScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.bottomBarNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    navigation(startDestination = ScreensNavItem.Home.route, route = "BottomBar_Route") {

        composable(ScreensNavItem.Medicine.route) {

            MedicineScreen(sharedViewModel)
        }

        composable(ScreensNavItem.Home.route) {

            HomeScreen(navHostController,sharedViewModel)
        }

        composable(ScreensNavItem.Setting.route) {

            SettingScreen(sharedViewModel, navHostController)
        }
    }


}