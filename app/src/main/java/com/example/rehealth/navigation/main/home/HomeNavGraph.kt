package com.example.rehealth.navigation.main.home

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.navigation.routes.Routes.DrugHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.DrugNotification
import com.example.rehealth.navigation.routes.Routes.Message
import com.example.rehealth.navigation.routes.Routes.QuestionDoneScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuestionsScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuizHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuizNotification
import com.example.rehealth.navigation.routes.Routes.TestHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestNotification
import com.example.rehealth.navigation.routes.Routes.VisitHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitNotification
import com.example.rehealth.ui.screens.main.home.HomeScreen
import com.example.rehealth.ui.screens.main.home.drug.DrugHomeScreen
import com.example.rehealth.ui.screens.main.home.quiz.DoneScreen
import com.example.rehealth.ui.screens.main.home.quiz.ExamScreen
import com.example.rehealth.ui.screens.main.home.quiz.QuizHomeScreen
import com.example.rehealth.ui.screens.main.home.test.TestHomeScreen
import com.example.rehealth.ui.screens.main.home.visit.VisitHomeScreen
import com.example.rehealth.ui.screens.main.medicine.MedicineScreen
import com.example.rehealth.ui.screens.main.setting.SettingScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {


    navigation(
        startDestination = ScreensNavItem.Home.route,
        route = ScreensNavItem.Home.parentRoute
    ) {

        //Home Screens bottom bar
        composable(ScreensNavItem.Medicine.route) {

            MedicineScreen(sharedViewModel)
        }

        composable(ScreensNavItem.Home.route) {

            HomeScreen(navHostController, sharedViewModel)
        }

        composable(ScreensNavItem.Setting.route) {

            SettingScreen(navHostController)
        }


        //items...............................................
        composable(
            DrugHomeScreenRoute,
            arguments = listOf(navArgument(Message) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "$DrugNotification/$Message={$Message}"
            })
        ) {
            val argument = it.arguments
            val drugAlarmId = argument?.getString(Message)

            LaunchedEffect(key1 = drugAlarmId) {

                if (drugAlarmId != null) {
                    sharedViewModel.drugAlarmId.value = drugAlarmId.toInt()
                    sharedViewModel.updateDrugAssociation()
                }
            }



            DrugHomeScreen(sharedViewModel, navHostController)
        }

        composable(
            VisitHomeScreenRoute,
            arguments = listOf(navArgument(Message) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "$VisitNotification/$Message={$Message}"
            })
        ) {

            val argument = it.arguments
            val visitAlarmId = argument?.getString(Message)

            LaunchedEffect(key1 = visitAlarmId) {
                if (visitAlarmId != null) {
                    sharedViewModel.visitAlarmId.value = visitAlarmId.toInt()
                    sharedViewModel.updateVisitAssociation()
                }
            }

            VisitHomeScreen(sharedViewModel, navHostController)
        }
        composable(
            TestHomeScreenRoute,
            arguments = listOf(navArgument(Message) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink { uriPattern = "$TestNotification/$Message={$Message}" })
        ) {

            val arguments = it.arguments

            val testAlarmId = arguments?.getString(Message)

            LaunchedEffect(key1 = testAlarmId) {
                if (testAlarmId != null) {

                    sharedViewModel.testAlarmId.value = testAlarmId.toInt()
                    sharedViewModel.updateTestAssociation()
                }
            }


            TestHomeScreen(sharedViewModel, navHostController)
        }


        //quiz Screens
        composable(
            QuizHomeScreenRoute,
            arguments = listOf(navArgument(Message) { type = NavType.StringType }),
            deepLinks = listOf(navDeepLink {
                uriPattern = "$QuizNotification/$Message={$Message}"
            })
        ) {

            val arguments = it.arguments

            val quizAlarmId = arguments?.getString(Message)

            if (quizAlarmId != null)
                sharedViewModel.quizAlarmId.value = quizAlarmId.toInt()

            QuizHomeScreen(navHostController, sharedViewModel)
        }


        composable(
            QuestionsScreenRoute,
            arguments = listOf(navArgument("quizType") { type = NavType.IntType })
        ) { backStack ->

            val quizType = backStack.arguments?.getInt("quizType")
            if (quizType != null) {
                sharedViewModel.quizType.value = quizType
            }

            ExamScreen(sharedViewModel, navHostController)
        }

        composable(QuestionDoneScreenRoute) {

            DoneScreen(sharedViewModel, navHostController)
        }

    }
}