package com.example.rehealth.navigation.main

import com.example.rehealth.R

enum class ScreensNavItem(
    val route: String,
    val parentRoute: String,
    val title: String,
    val icon: Int,
    val selectedIcon: Int
) {

    Setting(
        "setting_screen",
        "Setting_Route",
        "تنطیمات",
        R.drawable.ic_setting,
        R.drawable.ic_setting_filled
    ),
    Home(
        "home_screen",
        "Home_Route",
        "خانه", R.drawable.ic_home,
        R.drawable.ic_home_filled
    ),
    Medicine(
        "medicine_screen",
        "Medicine_Route",
        "اطلاعات دارویی",
        R.drawable.ic_medicine,
        R.drawable.ic_medicine_filled
    )
}