package com.example.rehealth.ui.screens.sideeffects

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.rehealth.ui.viewmodel.SharedViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MedicineInformation(sharedViewModel: SharedViewModel, medicineType: Int) {

    val pagerState = rememberPagerState(initialPage = 1)
    val scope = rememberCoroutineScope()

    val medicineWithSideEffects by sharedViewModel.medicineWithSideEffects.collectAsState()

    val getAllData by sharedViewModel.allData.collectAsState()


    Scaffold(topBar = {
        TabRowDesign(selectedIndex = pagerState.currentPage) {


            scope.launch {

                pagerState.animateScrollToPage(it.ordinal)

            }
        }
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            HorizontalPager(state = pagerState, pageCount = 2) { index ->

                Column(modifier = Modifier.fillMaxSize()) {
                    when (index) {

                        0 -> {
//                            SideEffectScreen(sharedViewModel, medicineType)
                            AdviceScreen(getAllData, medicineType)
                        }

                        1 -> {
                            SideEffectScreen(medicineType, medicineWithSideEffects)
//                            Text(text = "sina2")

                        }

                        else -> {
                            Text(text = "sina3")
                        }
                    }
                }

            }


        }
    }


}
