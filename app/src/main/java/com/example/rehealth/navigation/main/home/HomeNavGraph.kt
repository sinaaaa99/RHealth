package com.example.rehealth.navigation.main.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.rehealth.navigation.main.ScreensNavItem
import com.example.rehealth.navigation.routes.Routes.DrugHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuestionsScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuizHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitHomeScreenRoute
import com.example.rehealth.ui.screens.main.home.drug.DrugHomeScreen
import com.example.rehealth.ui.screens.main.home.quiz.ExamScreen
import com.example.rehealth.ui.screens.main.home.quiz.QuizHomeScreen
import com.example.rehealth.ui.screens.main.home.test.TestHomeScreen
import com.example.rehealth.ui.screens.main.home.visit.VisitHomeScreen
import com.example.rehealth.ui.viewmodel.SharedViewModel

fun NavGraphBuilder.homeNavGraph(
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    navigation(
        startDestination = ScreensNavItem.Home.route,
        route = ScreensNavItem.Home.parentRoute
    ) {

        composable(DrugHomeScreenRoute) {

            DrugHomeScreen(sharedViewModel)
        }

        composable(VisitHomeScreenRoute) {

            VisitHomeScreen(sharedViewModel)
        }
        composable(TestHomeScreenRoute) {

            TestHomeScreen(sharedViewModel)
        }


        //quiz Screens
        composable(QuizHomeScreenRoute) {

            QuizHomeScreen(navHostController)
        }

        composable(QuestionsScreenRoute,
            arguments = listOf(navArgument("quizType") { type = NavType.IntType })) {backStack ->

            val quizType = backStack.arguments?.getInt("quizType")
            if (quizType != null) {
                sharedViewModel.quizType.value = quizType
            }

            ExamScreen(sharedViewModel, navHostController)
        }

    }
}