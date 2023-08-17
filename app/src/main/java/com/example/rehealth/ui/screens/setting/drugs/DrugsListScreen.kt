package com.example.rehealth.ui.screens.setting.drugs

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.rehealth.ui.viewmodel.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugsListScreen(sharedViewModel: SharedViewModel, onFABClick: (Int) -> Unit) {


    Scaffold(
        floatingActionButton = {

            FloatingActionButtonDrug(onFABClick)
        }
    ) {


    }

}

@Composable
@Preview
fun DrugsListShow(onFABClick: (Int) -> Unit = {}) {

//    DrugsListScreen(onFABClick)
}