package com.example.rehealth.ui.screens.main.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.theme.green31
import com.example.rehealth.ui.theme.green32

@Composable
fun SettingItemmm() {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(shape = RoundedCornerShape(32.dp))
            .height(200.dp)
            .background(color = green30)

    ) {

        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .padding(end = 20.dp, top = 16.dp)
        ) {


            Text(text = "ویزیت", style = MaterialTheme.typography.titleLarge)

            Box(contentAlignment = Alignment.BottomStart) {

                /*Icon(
                    painter = painterResource(id = R.drawable.ic_drop),
                    contentDescription = "",
                    tint = green31
                )*/

                Box(contentAlignment = Alignment.BottomCenter) {

                    /*Icon(
                        modifier = Modifier.padding(start = 16.dp),
                        painter = painterResource(id = R.drawable.ic_drop),
                        contentDescription = "",
                        tint = green32
                    )*/

                    BoxIconShape()
                }


            }

        }


    }

}

@Composable
@Preview
fun SettingMenuItemPreview() {

//    SettingMenuItem()

}

@Composable
fun BoxIconShape() {
    Box(
        modifier = Modifier
            .padding(bottom = 16.dp, end = 8.dp)
            .clip(RoundedCornerShape(4.dp))
            .size(16.dp)
            .background(green30), contentAlignment = Alignment.Center
    ) {

        /*Icon(
            modifier = Modifier.padding(4.dp),
            painter = painterResource(id = R.drawable.ic_drop_out),
            contentDescription = "", tint = Color.Black
        )*/
    }
}
