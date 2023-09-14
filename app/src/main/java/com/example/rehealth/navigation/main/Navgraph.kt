package com.example.rehealth.navigation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.navigation.NavigateBetweenScreens
import com.example.rehealth.navigation.bottombar.bottomBarNavGraph
import com.example.rehealth.navigation.main.home.homeNavGraph
import com.example.rehealth.navigation.medicine.medicineNavGraph
import com.example.rehealth.navigation.setting.settingNavGraph
import com.example.rehealth.ui.viewmodel.SharedViewModel

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    visitScheduler: VisitScheduler,
    drugScheduler: DrugScheduler,
    testScheduler: TestScheduler
) {

    val navBetScreens = remember(navHostController) {
        NavigateBetweenScreens(navHostController)
    }
    NavHost(
        navController = navHostController,
        startDestination = "BottomBar_Route",
        route = "Main_Route"
    ) {

        bottomBarNavGraph(navHostController, sharedViewModel)

        homeNavGraph(navHostController,sharedViewModel)


        medicineNavGraph(navHostController, sharedViewModel)


        settingNavGraph(
            navHostController,
            sharedViewModel,
            visitScheduler,
            drugScheduler,
            testScheduler,
            navBetScreens.drugListToAdd,
            navBetScreens.testListToAdd,
            navBetScreens.visitListToAdd
        )

    }
}