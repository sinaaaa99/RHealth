package com.example.rehealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.rehealth.ui.screens.sideeffects.MedicineInformation
import com.example.rehealth.ui.theme.ReHealthTheme
import com.example.rehealth.ui.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineActivity : ComponentActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val medicineType = intent.getIntExtra("medicineType", 1)
//        val sina =sharedViewModel.medicineWithSideEffects.value
//        sharedViewModel.getMedicineWithSideEffects()
        setContent {
            ReHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MedicineInformation(sharedViewModel, medicineType)

                }
            }
        }
    }
}
