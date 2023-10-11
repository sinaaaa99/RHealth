package com.example.rehealth.ui.screens.setting.association.drug

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.theme.associationIconBackground
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun DrugAssociationScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel) {

    val allDrugs by sharedViewModel.allDrugs.collectAsState()

    var allAlarms by remember {
        mutableStateOf(0L)
    }
    var allUserAssociation by remember {
        mutableStateOf(0L)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(associationBackground)
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navHostController.popBackStack() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "پاسخ بیمار به هشدار دارو", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }


            if (allDrugs is RequestState.Success) {

                val drugs = (allDrugs as RequestState.Success<List<DrugReminder>>).data

                drugs.forEachIndexed { index, data ->

                    val dateRing =
                        data.reminder.atZone(ZoneId.systemDefault()).toEpochSecond()
                    val dateNow =
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()

                    val diff = dateNow.minus(dateRing).toDouble()

//                    Log.d("ringCountCalculate", diff.toString())

                    val ringCountCalculate = diff / 60 / 60 / 24

//                    Log.d("ringCountCalculate", ringCountCalculate.toString())

                    val ringCount = ringCountCalculate.toLong() + 1

//                    Log.d("ringCountCalculate", ringCount.toString())

                    LaunchedEffect(Unit) {

                        allAlarms += ringCount

                        allUserAssociation += data.userAssociation

                    }

                    DrugAssociationItem(data, index + 1, ringCount)

                }

                /*itemsIndexed((allDrugs as RequestState.Success<List<DrugReminder>>).data) { index, data ->

                    val dateRing =
                        data.reminder.atZone(ZoneId.systemDefault()).toEpochSecond()
                    val dateNow =
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()

                    val diff = dateNow.minus(dateRing).toDouble()

//                    Log.d("ringCountCalculate", diff.toString())

                    val ringCountCalculate = diff / 60 / 60 / 24

//                    Log.d("ringCountCalculate", ringCountCalculate.toString())

                    val ringCount = ringCountCalculate.toLong() + 1

//                    Log.d("ringCountCalculate", ringCount.toString())

                    LaunchedEffect(Unit) {

                        allAlarms += ringCount

                        allUserAssociation += data.userAssociation

                    }

                    DrugAssociationItem(data, index + 1, ringCount)

                }*/

        }

        ResultCardDrugAssociation(userAssociation = allUserAssociation, alarms = allAlarms)


    }
}

@Composable
fun ResultCardDrugAssociation(userAssociation: Long, alarms: Long) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), horizontalAlignment = Alignment.End
        ) {
            androidx.compose.material.Text(
                modifier = Modifier.fillMaxWidth(),
                text = "آمار کلی",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {

                //association.............................
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(associationIconBackground)
                            .padding(4.dp), contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_bell_hand),
                            contentDescription = "icon bell hand",
                            tint = Color.White
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.material.Text(
                            text = userAssociation.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = associationIconBackground
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        androidx.compose.material.Text(
                            text = "مشارکت",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .height(50.dp)
                        .width(1.dp), color = Color.Black
                )

                //bell......................................
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(associationIconBackground)
                            .padding(6.dp), contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_bell),
                            contentDescription = "icon bell ",
                            tint = Color.White
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        androidx.compose.material.Text(
                            text = alarms.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = associationIconBackground
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        androidx.compose.material.Text(
                            text = "هشدارها",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }


        }

    }
}