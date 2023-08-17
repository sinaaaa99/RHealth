package com.example.rehealth.navigation.medicine

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.medicineNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    navigation(startDestination = ScreensNavItem.Medicine.route, route = ScreensNavItem.Medicine.parentRoute) {

    }
}