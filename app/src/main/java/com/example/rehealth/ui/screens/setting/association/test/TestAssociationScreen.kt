package com.example.rehealth.ui.screens.setting.association.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@Composable
fun TestAssociationScreen(navHostController: NavHostController,sharedViewModel: SharedViewModel) {

    sharedViewModel.getTestAssociation()
    sharedViewModel.getTestSize()

    val getTestAssociation by sharedViewModel.testAssociation.collectAsState()
    val getTestSize by sharedViewModel.testSize.collectAsState()




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(associationBackground)
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

            androidx.compose.material.Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "پاسخ بیمار به هشدار آزمایش", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        Spacer(modifier = Modifier.height(12.dp))

        if (getTestAssociation is RequestState.Success && getTestSize is RequestState.Success) {

            val testAssociation = getTestAssociation as RequestState.Success<Int>
            val testSize = getTestSize as RequestState.Success<Int>


            val associationPercent by remember(testSize) {
                mutableStateOf((testAssociation.data.toFloat() / testSize.data.toFloat()))
            }


            Row(modifier = Modifier.fillMaxWidth()) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = testAssociation.data.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "مشارکت های بیمار",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = testSize.data.toString(),
                            style = MaterialTheme.typography.titleMedium
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(text = "کل هشدارها", style = MaterialTheme.typography.titleMedium)
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = if (testSize.data!=0) " ${associationPercent * 100} %" else "0",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "درصد مشارکت بیمار", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

    }
}