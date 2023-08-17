package com.example.rehealth.navigation.main.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.rehealth.navigation.main.ScreensNavItem

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController){

    navigation(startDestination = ScreensNavItem.Home.route, route = ScreensNavItem.Home.parentRoute) {

    }
}