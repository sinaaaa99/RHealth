package com.example.rehealth.ui.screens.setting.drugs

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun FloatingActionButtonDrug(
    onFABClick: (Int) -> Unit
) {

    FloatingActionButton(onClick = { onFABClick(-1) }) {

        Icon(imageVector = Icons.Filled.Add, contentDescription = "FAB Icon")

    }
}