package com.example.rehealth.ui.screens.setting.drugs

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsListScreen(
    sharedViewModel: SharedViewModel,
    drugScheduler: DrugScheduler,
    onFABClick: (Int) -> Unit
) {

    val allDrugs by sharedViewModel.allDrugs.collectAsState()
    var drugId by sharedViewModel.drugId
    var alarmId by sharedViewModel.alarmId


    Scaffold(floatingActionButton = {

        FloatingActionButton(onFABClick)
    }) {

        LazyColumn {

            if (allDrugs is RequestState.Success) {

                itemsIndexed(
                    (allDrugs as
                            RequestState.Success<List<DrugReminder>>).data
                ) { _, data ->


                    DrugItem(shiftCode = data.shiftCode) {

                        drugId = data.drugId
                        alarmId = data.alarmId


                        drugScheduler.cancel(data)

                        sharedViewModel.deleteDrugReminder()
                        sharedViewModel.deleteShiftDrug()

                    }


                }
            }
        }


    }

}

@Composable
@Preview
fun DrugsListShow(onFABClick: (Int) -> Unit = {}) {

//    DrugsListScreen(onFABClick)
}