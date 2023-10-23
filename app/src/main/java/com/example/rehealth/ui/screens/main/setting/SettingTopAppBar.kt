package com.example.rehealth.ui.screens.main.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R

@Composable
fun SettingTopAppBar(
    onSettingClick: () -> Unit,
    onProfileClick: () -> Unit,
    onResetClick: () -> Unit
) {

    TopAppBar(
        backgroundColor = Color.White
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            onSettingClick()
                        },
                        painter = painterResource(id = R.drawable.ic_setting_menu),
                        contentDescription = "",
                        tint = Color.Black
                    )
                }

                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        modifier = Modifier.clickable {
                            onResetClick()
                        },
                        painter = painterResource(id = R.drawable.ic_reset),
                        contentDescription = "reset icon"
                    )
                }

            }


            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .clickable {
                            onProfileClick()
                        },
                    painter = painterResource(id = R.drawable.ic_profile_setting),
                    contentDescription = ""
                )
            }

        }

    }
}

@Composable
@Preview
fun SettingTopAppBarPre() {

//    SettingTopAppBar()
}