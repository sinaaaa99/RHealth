package com.example.rehealth.ui.screens.main.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomShape() {

    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp) ){


    }

}

@Composable
@Preview(showBackground = true)
fun CustomShapePreview() {
    CustomShape()}