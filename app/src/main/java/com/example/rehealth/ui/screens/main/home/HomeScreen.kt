package com.example.rehealth.ui.screens.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rehealth.R
import com.example.rehealth.data.models.HomeMenuItemClass
import com.example.rehealth.ui.theme.blue30
import com.example.rehealth.ui.theme.green20
import com.example.rehealth.ui.theme.menuItemColor1
import com.example.rehealth.ui.theme.menuItemColor2
import com.example.rehealth.ui.theme.menuItemColor3
import com.example.rehealth.ui.theme.menuItemColor4
import com.example.rehealth.ui.theme.red50
import com.example.rehealth.ui.theme.yellow70

@Composable
fun HomeScreen() {

    val listOfItems = listOf(
        HomeMenuItemClass(
            MaterialTheme.colorScheme.background,
            menuItemColor1,
            "داروها",
            "دارو",
            "0",
            R.drawable.pic_drug_home
        ),
        HomeMenuItemClass(menuItemColor1, menuItemColor2, "ویزیت ها", "ویزیت", "0", R.drawable.pic_drug_home),
        HomeMenuItemClass(menuItemColor2, menuItemColor3, "آزمایش ها", "آزمایش", "0", R.drawable.pic_drug_home),
        HomeMenuItemClass(menuItemColor3, menuItemColor4, "پرسشنامه", "", "", R.drawable.pic_drug_home)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn {

            items(listOfItems) { menuItem ->

                MenuItemHome(menuItem)

            }
        }


    }
}