package com.example.rehealth.ui.screens.sideeffects

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.rehealth.navigation.medicine.TabPages

@Composable
fun TabRowDesign(selectedIndex: Int, onSelect: (TabPages) -> Unit) {


    TabRow(selectedTabIndex = selectedIndex) {

        TabPages.values().forEachIndexed { index, tabPages ->

            Tab(selected = index == selectedIndex,
                onClick = { onSelect(tabPages) }, text = {
                    Text(text = tabPages.route)
                })


        }

    }
}