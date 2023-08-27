package com.example.rehealth.navigation

import androidx.navigation.NavHostController
import com.example.rehealth.navigation.routes.Routes.TestAddScreenRoute

class NavigateBetweenScreens(navHostController: NavHostController) {

    val drugListToAdd: (Int) -> Unit = { drugId ->

        navHostController.navigate("DrugAddScreenRoute/$drugId")
    }

    val testListToAdd: (Int) -> Unit = { testId ->

        navHostController.navigate("TestAddScreenRoute/$testId")

    }

    val visitListToAdd: (Int) -> Unit = { visitId ->

        navHostController.navigate("VisitAddScreenRoute/$visitId")

    }
}