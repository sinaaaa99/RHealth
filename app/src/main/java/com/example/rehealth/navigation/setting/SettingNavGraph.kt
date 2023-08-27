package com.example.rehealth.navigation.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.navigation.routes.Routes.DrugAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.DrugListScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestListScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitListScreenRoute
import com.example.rehealth.ui.screens.setting.visits.AddVisitScreen
import com.example.rehealth.ui.screens.setting.drugs.AddDrugScreen
import com.example.rehealth.ui.screens.setting.drugs.DrugsListScreen
import com.example.rehealth.ui.screens.setting.tests.AddTestScreen
import com.example.rehealth.ui.screens.setting.tests.TestListScreen
import com.example.rehealth.ui.screens.setting.visits.VisitListScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.settingNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    visitScheduler: VisitScheduler,
    drugScheduler: DrugScheduler,
    testScheduler: TestScheduler,
    onDrugFABClick: (Int) -> Unit,
    onTestFABClick: (Int) -> Unit,
    onVisitFABClick: (Int) -> Unit,
) {


    navigation(
        startDestination = ScreensNavItem.Setting.route,
        route = ScreensNavItem.Setting.parentRoute
    ) {


        //visit Screens
        composable(VisitListScreenRoute) {

            VisitListScreen(sharedViewModel,visitScheduler, onVisitFABClick)

        }

        composable(VisitAddScreenRoute) {

            AddVisitScreen(sharedViewModel,navHostController, visitScheduler)
        }

        //Drug Screens
        composable(DrugListScreenRoute) {

            DrugsListScreen(sharedViewModel, drugScheduler, onDrugFABClick)
        }

        composable(DrugAddScreenRoute) {
            AddDrugScreen(sharedViewModel, drugScheduler, navHostController)
        }


        //Tests Screens
        composable(TestListScreenRoute) {

            TestListScreen(testScheduler, sharedViewModel, onTestFABClick)
        }

        composable(TestAddScreenRoute) {

            AddTestScreen(testScheduler, sharedViewModel, navHostController)
        }

    }
}