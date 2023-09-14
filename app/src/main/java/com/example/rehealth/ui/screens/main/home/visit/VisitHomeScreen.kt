package com.example.rehealth.ui.screens.main.home.visit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.ui.theme.drugBackgroundColor
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@Composable
fun VisitHomeScreen(sharedViewModel: SharedViewModel) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(drugBackgroundColor)) {


//        NonFoundScreen(name = "ویزیتی")

        val visits by sharedViewModel.allVisit.collectAsState()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Icon(modifier=Modifier.size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "ویزیت های شما", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        LazyColumn {

            if (visits is RequestState.Success){

                itemsIndexed((visits as RequestState.Success<List<VisitReminder>>).data) { index,visits ->

                    val itemNumber = index + 1

                    VisitHomeItem(visitReminder = visits,itemNumber = itemNumber)
                }
            }
        }
    }
}