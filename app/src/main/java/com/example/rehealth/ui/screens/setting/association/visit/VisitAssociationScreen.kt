package com.example.rehealth.ui.screens.setting.association.visit

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
fun VisitAssociationScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel) {

    sharedViewModel.getVisitAssociation()
    sharedViewModel.getVisitSize()

    val getVisitAssociation by sharedViewModel.visitAssociation.collectAsState()
    val getVisitSize by sharedViewModel.visitSize.collectAsState()




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
                text = "پاسخ بیمار به هشدار ویزیت", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        Spacer(modifier = Modifier.height(12.dp))

        if (getVisitAssociation is RequestState.Success && getVisitSize is RequestState.Success) {

            val visitAssociation = getVisitAssociation as RequestState.Success<Int>
            val visitSize = getVisitSize as RequestState.Success<Int>

            val associationPercent by remember(visitSize) {
                mutableStateOf((visitAssociation.data.toFloat() / visitSize.data.toFloat()))
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
                            text = visitAssociation.data.toString(),
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
                            text = visitSize.data.toString(),
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
                        text = if (visitSize.data != 0) " ${associationPercent * 100} %" else "0",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "درصد مشارکت بیمار", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

    }
}