package com.example.rehealth.ui.screens.sideeffects


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.util.RequestState

@Composable
fun AdviceScreen(
    allData: RequestState<List<Medicines>>,
    medicineType: Int
) {

    val scrollable = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollable)) {

        if (allData is RequestState.Success) {

            val advice = allData.data[medicineType].advice

            Text(
                text = advice,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge
            )
        }


    }
}