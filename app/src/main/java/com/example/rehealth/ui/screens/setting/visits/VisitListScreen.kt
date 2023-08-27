package com.example.rehealth.ui.screens.setting.visits

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rehealth.data.interfaces.VisitScheduler
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.ui.screens.setting.drugs.FloatingActionButton
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitListScreen(
    sharedViewModel: SharedViewModel,
    visitScheduler: VisitScheduler,
    onVisitFabClick: (Int) -> Unit
) {

    val listOfVisits by sharedViewModel.allVisit.collectAsState()

    Scaffold(floatingActionButton = { FloatingActionButton(onFABClick = onVisitFabClick) }) {

        Column(modifier = Modifier.padding(it)) {

            LazyColumn {

                if (listOfVisits is RequestState.Success) {

                    itemsIndexed((listOfVisits as RequestState.Success<List<VisitReminder>>).data)
                    { _, visitData ->

                        VisitItem(title = visitData.title) {

                            sharedViewModel.visitId.value = visitData.visitId

                            visitScheduler.cancel(visitData)

                            sharedViewModel.deleteVisit()

                        }


                    }
                }
            }


        }

    }

}

@Composable
@Preview
fun VisitListScreenPreview() {

//    VisitListScreen()
}