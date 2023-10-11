package com.example.rehealth.ui.screens.setting.drugs

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
import androidx.navigation.NavHostController
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsListScreen(
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController,
    drugScheduler: DrugScheduler,
    onFABClick: (Int) -> Unit
) {

    val allDrugs by sharedViewModel.allDrugs.collectAsState()


    Scaffold(floatingActionButton = {

        FloatingActionButton(onFABClick)
    }) {

        LazyColumn(modifier = Modifier.padding(it)) {

            if (allDrugs is RequestState.Success) {

                itemsIndexed(
                    (allDrugs as
                            RequestState.Success<List<DrugReminder>>).data
                ) { _, data ->


                    DrugItem(shiftCode = data.shiftCode, ({

                        //onTrash Click
                        sharedViewModel.reminderId.value = data.reminderId
                        sharedViewModel.alarmId.value = data.alarmId


                        drugScheduler.cancel(data)

                        sharedViewModel.deleteDrugReminder()

                    })) {
                        //On card Click
                        navHostController.navigate("UpdateDrugsList/${data.reminderId}")

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