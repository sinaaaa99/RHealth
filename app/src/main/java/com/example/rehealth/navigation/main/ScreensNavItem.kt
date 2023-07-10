package com.example.rehealth.navigation.main

import com.example.rehealth.R

enum class ScreensNavItem(
    val route: String,
    val title: String,
    val icon: Int,
    val selectedIcon: Int
) {

    Setting("setting_screen", "تنطیمات", R.drawable.ic_setting, R.drawable.ic_setting_filled),
    Home("home_screen", "خانه",  R.drawable.ic_home, R.drawable.ic_home_filled),
    Medicine("medicine_screen", "اطلاعات دارویی",  R.drawable.ic_medicine, R.drawable.ic_medicine_filled)
}