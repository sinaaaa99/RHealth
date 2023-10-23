package com.example.rehealth.navigation.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.navigation.routes.Routes.DrugAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.DrugListScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestListScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitAddScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitListScreenRoute
import com.example.rehealth.ui.screens.setting.association.AssociationMainScreen
import com.example.rehealth.ui.screens.setting.association.drug.DrugAssociationScreen
import com.example.rehealth.ui.screens.setting.association.test.TestAssociationScreen
import com.example.rehealth.ui.screens.setting.association.visit.VisitAssociationScreen
import com.example.rehealth.ui.screens.setting.visits.AddVisitScreen
import com.example.rehealth.ui.screens.setting.drugs.AddReminderScreen
import com.example.rehealth.ui.screens.setting.drugs.DrugsListScreen
import com.example.rehealth.ui.screens.setting.drugs.UpdateDrugsScreen
import com.example.rehealth.ui.screens.setting.lock.AddLockScreen
import com.example.rehealth.ui.screens.setting.quiz.AddQuizAlarmScreen
import com.example.rehealth.ui.screens.setting.quiz.ListQuizAlarmScreen
import com.example.rehealth.ui.screens.setting.tests.AddTestScreen
import com.example.rehealth.ui.screens.setting.tests.TestListScreen
import com.example.rehealth.ui.screens.setting.user.UserIdentifyScreen
import com.example.rehealth.ui.screens.setting.visits.VisitListScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel
import java.util.UUID

fun NavGraphBuilder.settingNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel,
    visitScheduler: VisitScheduler,
    drugScheduler: DrugScheduler,
    testScheduler: TestScheduler,
    quizScheduler: QuizScheduler,
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

            VisitListScreen(sharedViewModel, visitScheduler, onVisitFABClick)

        }

        composable(VisitAddScreenRoute) {

            AddVisitScreen(sharedViewModel, navHostController, visitScheduler)
        }

        //Drug Screens
        composable(DrugListScreenRoute) {

            DrugsListScreen(sharedViewModel, navHostController, drugScheduler, onDrugFABClick)
        }

        composable(DrugAddScreenRoute) {
            AddReminderScreen(sharedViewModel, drugScheduler, navHostController)
        }

        composable(
            "UpdateDrugsList/{drugId}",
            arguments = listOf(navArgument("drugId") { type = NavType.StringType })
        ) {

            val argument = it.arguments

            val drugId = argument?.getString("drugId")

            sharedViewModel.reminderId.value = UUID.fromString(drugId)

            UpdateDrugsScreen(sharedViewModel, navHostController)
        }


        //Tests Screens
        composable(TestListScreenRoute) {

            TestListScreen(testScheduler, sharedViewModel, onTestFABClick)
        }

        composable(TestAddScreenRoute) {

            AddTestScreen(testScheduler, sharedViewModel, navHostController)
        }

        //Quiz Reminder Screens
        composable("AddQuizReminderScreen") {
            AddQuizAlarmScreen(quizScheduler, navHostController, sharedViewModel)
        }
        composable("ListQuizReminderScreen") {
            ListQuizAlarmScreen(sharedViewModel, quizScheduler, navHostController)
        }


        //Association Screens.........................
        composable("AssociationMainScreen") {

            AssociationMainScreen(navHostController)
        }

        //drug
        composable("AssociationDrugScreen") {

            DrugAssociationScreen(navHostController, sharedViewModel)
        }

        //visit
        composable("AssociationVisitScreen") {

            VisitAssociationScreen(navHostController, sharedViewModel)
        }

        //test
        composable("AssociationTestScreen") {

            TestAssociationScreen(navHostController, sharedViewModel)
        }

        //UserIdentifyScreen
        composable("UserIdentifyScreen") {

            UserIdentifyScreen(sharedViewModel, navHostController)
        }

        //Lock Screen
        composable("LockScreen") {

            AddLockScreen(sharedViewModel, navHostController)
        }
    }
}