package com.example.rehealth.navigation.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rehealth.data.interfaces.AlarmScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.ui.screens.setting.VisitOption
import com.example.rehealth.ui.screens.setting.drugs.AddDrugScreen
import com.example.rehealth.ui.screens.setting.drugs.DrugsListScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.settingNavGraph(navHostController: NavHostController,sharedViewModel:SharedViewModel, alarmSchedule: AlarmScheduler, drugScheduler: DrugScheduler, onFABClick: (Int) -> Unit){


    navigation(
        startDestination = ScreensNavItem.Setting.route,
        route = ScreensNavItem.Setting.parentRoute
    ){

        composable(route = "VisitOption_Screen"){

            VisitOption(alarmSchedule)
        }
        composable("DrugOption_Screen"){

            DrugsListScreen(sharedViewModel  ,onFABClick)
        }

        composable("AddDrug_Screen/{id}"){
            AddDrugScreen(sharedViewModel, drugScheduler)
        }

    }
}