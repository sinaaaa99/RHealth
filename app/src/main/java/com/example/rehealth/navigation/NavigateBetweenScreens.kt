package com.example.rehealth.navigation

import androidx.navigation.NavHostController

class NavigateBetweenScreens(navHostController: NavHostController) {

    val addDrug:(Int)->Unit = { drugId->

        navHostController.navigate("AddDrug_Screen/$drugId")
    }
}