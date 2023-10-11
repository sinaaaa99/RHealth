package com.example.rehealth.ui.screens.main.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SettingItems(
    image: Int,
    title: String,
    bacColor: Color,
    height: Dp,
    onItemClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(height)
            .clip(RoundedCornerShape(32.dp))
            .background(color = bacColor)
            .clickable {

                onItemClick()
            }
    ) {

        Column {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = image),
                    contentDescription = ""
                )
            }

            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
                /*Spacer(modifier = Modifier.height(1.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    text = "0 $title",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )*/

            }
        }
    }

}
