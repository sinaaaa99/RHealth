package com.example.rehealth.ui.screens.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.HomeMenuItemClass

@Composable
fun MenuItemHome(menuItem:HomeMenuItemClass) {


    Card(
        modifier = Modifier
            .background(color = menuItem.backgroundColor)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(topStart = 50.dp),
        colors = CardDefaults.cardColors(containerColor = menuItem.cardColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {

                Image(
                    painter = painterResource(id = menuItem.image),
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = menuItem.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(
                        text = menuItem.subTitle,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = menuItem.count,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }


        }
    }

}

@Composable
@Preview
fun MenuItemHomePreview() {
//    MenuItemHome(menuIte)
}