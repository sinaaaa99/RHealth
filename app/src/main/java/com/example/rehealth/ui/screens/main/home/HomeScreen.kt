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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.HomeMenuItemClass
import com.example.rehealth.data.models.quiz.QuizResult
import com.example.rehealth.ui.theme.menuCardColor
import com.example.rehealth.ui.theme.menuItemColor1
import com.example.rehealth.ui.theme.menuItemColor2
import com.example.rehealth.ui.theme.menuItemColor3
import com.example.rehealth.ui.theme.menuItemColor4
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@Composable
fun HomeScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel) {


    val listOfItems = listOf(
        HomeMenuItemClass(
            menuItemColor1,
            menuItemColor2,
            menuItemColor1,
            "دارو",
            150.dp
        ),
        HomeMenuItemClass(
            menuItemColor1,
            menuItemColor3,
            menuItemColor2,
            "پرسشنامه",
            120.dp
        ),
        HomeMenuItemClass(
            menuItemColor2,
            menuItemColor4,
            menuItemColor3,
            "آزمایش",
            120.dp
        ),
        HomeMenuItemClass(
            menuItemColor3,
            menuItemColor4,
            menuItemColor4,
            "ویزیت",
            170.dp
        )
    )

    LaunchedEffect(key1 = Unit ){

        sharedViewModel.getUserCheeks()
    }
    val usersCheeks by sharedViewModel.userCheeks.collectAsState()

    var patientState: Boolean? by remember(usersCheeks) {
        mutableStateOf(null)
    }


    if (usersCheeks is RequestState.Success) {

        val userCheeksResult = usersCheeks as RequestState.Success<QuizResult>

        patientState = userCheeksResult.data.userCheek1 > 6 || userCheeksResult.data.userCheek2 > 6


    }


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

            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

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
                        text = if (patientState == true) "هرچه سریعتر به پزشک خود مراجعه نمایید."
                        else "لطفا داروهای خود را به موقع مصرف نمایید ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )

                }
            }
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(menuItemColor4)
        ) {

            itemsIndexed(listOfItems) { index, menuItem ->

                MenuItemHome(menuItem) {

                    when (index) {

                        0 -> {
                            navHostController.navigate("DrugHomeScreenRoute/0")
                        }

                        1 -> {
                            navHostController.navigate("QuizHomeScreenRoute/0")
                        }

                        2 -> {
                            navHostController.navigate("TestHomeScreenRoute/0")
                        }

                        3 -> {
                            navHostController.navigate("VisitHomeScreenRoute/0")
                        }

                        else -> {}
                    }


                }

            }
        }


    }
}