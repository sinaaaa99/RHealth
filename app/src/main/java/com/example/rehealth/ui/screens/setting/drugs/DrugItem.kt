package com.example.rehealth.ui.screens.setting.drugs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R

@Composable
fun DrugItem(shiftCode: Int, onTrashClick: () -> Unit, onCardClick: () -> Unit) {

    val title = when (shiftCode) {
        1 -> "نوبت صبح"
        2 -> "نوبت ظهر"
        3 -> "نوبت عصر"
        4 -> "نوبت شب"
        else -> "نوبتی انتخاب نشده"


    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCardClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(35.dp)
                        .clickable { onTrashClick() },
                    painter = painterResource(id = R.drawable.ic_recyclerbin),
                    contentDescription = "Recycler bin icon"
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_pills),
                contentDescription = "pills item image"
            )


        }
    }

}

@Composable
@Preview
fun DrugItemShow() {

//    DrugItem("")
}