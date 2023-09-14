package com.example.rehealth.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.HomeMenuItemClass
import com.example.rehealth.navigation.routes.Routes.DrugHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuizHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestHomeScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitHomeScreenRoute
import com.example.rehealth.ui.theme.menuCardColor
import com.example.rehealth.ui.theme.menuItemColor1
import com.example.rehealth.ui.theme.menuItemColor2
import com.example.rehealth.ui.theme.menuItemColor3
import com.example.rehealth.ui.theme.menuItemColor4
import com.example.rehealth.ui.viewmodel.SharedViewModel

@Composable
fun HomeScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel) {


    val listOfItems = listOf(
        HomeMenuItemClass(
            menuItemColor1,
            menuItemColor2,
            menuItemColor1,
            "داروها",
            "دارو",
            "0",
            150.dp
        ),
        HomeMenuItemClass(
            menuItemColor1,
            menuItemColor3,
            menuItemColor2,
            "ویزیت ها",
            "ویزیت", "0",
            120.dp
        ),
        HomeMenuItemClass(
            menuItemColor2,
            menuItemColor4,
            menuItemColor3,
            "آزمایش ها",
            "آزمایش",
            "0",
            120.dp
        ),
        HomeMenuItemClass(
            menuItemColor3,
            menuItemColor4,
            menuItemColor4,
            "پرسشنامه",
            "",
            "",
            250.dp
        )
    )

    val testSize by sharedViewModel.testsSize
    val visitSize by sharedViewModel.visitsSize

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(menuItemColor1),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search icon home",
                tint = Color.Black
            )

            Text(
                text = "نمایه سینا ویسی",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Card(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
            colors = CardDefaults.cardColors(containerColor = menuCardColor)
        ) {

            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.pic_dr_advice),
                        contentDescription = "pic doctor advice home screen"
                    )
                }
                Column(modifier = Modifier.weight(8f)) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "توصیه امروز:",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "لطفا داروهای خود را به موقع مصرف نمایید ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )

                }
            }
        }


        LazyColumn {

            itemsIndexed(listOfItems) { index, menuItem ->

                MenuItemHome(menuItem) {

                    when (index) {

                        0 -> {
                            navHostController.navigate(DrugHomeScreenRoute)
                        }

                        1 -> {
                            navHostController.navigate(VisitHomeScreenRoute)
                        }

                        2 -> {
                            navHostController.navigate(TestHomeScreenRoute)
                        }

                        3 -> {
                            navHostController.navigate(QuizHomeScreenRoute)
                        }

                        else -> {}
                    }


                }

            }
        }


    }
}