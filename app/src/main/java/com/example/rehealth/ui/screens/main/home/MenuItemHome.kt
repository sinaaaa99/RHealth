package com.example.rehealth.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.HomeMenuItemClass

@Composable
fun MenuItemHome(menuItem: HomeMenuItemClass, onTextClick: () -> Unit) {


    Card(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        menuItem.backgroundColor1,
                        menuItem.backgroundColor2
                    )
                )
            )
            .fillMaxWidth()
            .height(menuItem.height),
        shape = RoundedCornerShape(topStart = 60.dp, bottomEnd = 60.dp),
        colors = CardDefaults.cardColors(containerColor = menuItem.cardColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp, horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {

                /*Image(
                    painter = painterResource(id = menuItem.image),
                    contentDescription = ""
                )*/
                /*Text(
                    text = itemSize.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace
                    )
                )*/
                Icon(
                    modifier = Modifier.size(35.dp).clickable { onTextClick() },
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "KeyboardArrowLeft"
                )
            }
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier.clickable {
                        onTextClick()
                    },
                    text = menuItem.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }


        }
    }

}

@Composable
@Preview
fun MenuItemHomePreview() {
//    MenuItemHome(menuIte)
}