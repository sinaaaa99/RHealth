package com.example.rehealth.ui.screens.main.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.navigation.routes.Routes.DrugListScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestListScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitListScreenRoute
import com.example.rehealth.ui.theme.cardColor
import com.example.rehealth.ui.theme.drugSettingBack
import com.example.rehealth.ui.theme.quizSettingBack
import com.example.rehealth.ui.theme.testSettingBack
import com.example.rehealth.ui.theme.visitSettingBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navHostController: NavHostController) {

    val scrollable = rememberScrollState()

    Scaffold(topBar = {
        SettingTopAppBar()
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollable)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.Center,
            ) {

                Text(
                    text = "سینا ویسی", style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "سلام,",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp), backgroundColor = cardColor, shape = MaterialTheme.shapes.large
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color = Color.White)
                    ) {

                        Image(
                            modifier = Modifier.padding(8.dp),
                            painter = painterResource(id = R.drawable.pic_setting_info),
                            contentDescription = ""
                        )
                    }


                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "میزان مشارکت شما",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "پاسخ به هشدار ها",
                            style = MaterialTheme.typography.labelSmall, color = Color.Gray
                        )
                    }


                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, end = 10.dp, start = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.setting_service),
                    contentDescription = ""
                )

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(modifier=Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.bottom_arrow),
                        contentDescription = ""
                    )

                    Text(
                        text = "خدمات",
                        modifier = Modifier
                            .padding(12.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(modifier = Modifier.weight(1f)) {

                    SettingItems(
                        image = R.drawable.pic_visit,
                        title = "ویزیت",
                        bacColor = visitSettingBack,
                        height = 200.dp
                    ){

                        navHostController.navigate(VisitListScreenRoute)

                    }

                    SettingItems(
                        image = R.drawable.pic_test_set,
                        title = "آزمایش",
                        bacColor = testSettingBack,
                        height = 250.dp
                    ){

                        navHostController.navigate(TestListScreenRoute)

                    }

                }

                Column(modifier = Modifier.weight(1f)) {

                    SettingItems(
                        image = R.drawable.pic_drug_set,
                        title = "دارو",
                        bacColor = drugSettingBack,
                        height = 250.dp
                    ){

                        navHostController.navigate(DrugListScreenRoute)

                    }

                    SettingItems(
                        image = R.drawable.pic_quiz,
                        title = "پرسشنامه",
                        bacColor = quizSettingBack,
                        height = 200.dp
                    ){

                    }

                }
            }
        }

    }
}