package com.example.rehealth.ui.screens.setting.association

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.association.AssociationMenuItem

@Composable
fun AssociationMainScreen(navHostController: NavHostController) {

    val menuList = listOf(
        AssociationMenuItem("داروها", R.drawable.ic_pills),
        AssociationMenuItem("آزمایش ها", R.drawable.ic_test),
        AssociationMenuItem("ویزیت ها", R.drawable.ic_visit)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Icon(
                modifier = Modifier.size(30.dp).clickable { navHostController.popBackStack() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "پاسخ بیمار به هشدارها", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        LazyColumn {

            itemsIndexed(menuList) { index, data ->

                AssociationItems(associationMenuItem = data) {

                    when (index) {

                        0 -> {
                            navHostController.navigate("AssociationDrugScreen")
                        }

                        1 -> {
                            navHostController.navigate("AssociationTestScreen")
                        }

                        2 -> {
                            navHostController.navigate("AssociationVisitScreen")
                        }

                    }
                }


            }
        }

    }
}