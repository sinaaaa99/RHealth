package com.example.rehealth.ui.screens.setting.tests

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rehealth.data.interfaces.TestScheduler
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.ui.screens.setting.drugs.FloatingActionButton
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestListScreen(
    testScheduler: TestScheduler,
    sharedViewModel: SharedViewModel,
    onTestFABClick: (Int) -> Unit
) {


    val listOfTests by sharedViewModel.allTest.collectAsState()


    Scaffold(floatingActionButton = {
        FloatingActionButton(onFABClick = onTestFABClick)
    }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            LazyColumn {


                if (listOfTests is RequestState.Success) {

                    itemsIndexed((listOfTests as RequestState.Success<List<TestReminder>>).data)
                    { _, tests ->


                        TestItem(title = tests.name) {

                            sharedViewModel.testId.value = tests.testId


                            testScheduler.cancel(tests)

                            sharedViewModel.deleteTest()



                        }
                    }
                }
            }

        }

    }

}

@Composable
@Preview
fun TestListScreenPreview() {

}