package com.example.rehealth.ui.screens.main.home.drug

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.DrugsClass
import com.example.rehealth.ui.theme.drugBackgroundColor
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugHomeScreen(sharedViewModel: SharedViewModel) {

    val timeShiftName by sharedViewModel.timeShiftName

    val shiftCode by sharedViewModel.shiftCode
    LaunchedEffect(shiftCode) {

        sharedViewModel.getDrugs()
    }

    val drugs by sharedViewModel.drugs.collectAsState()

    var expandedMenu by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(drugBackgroundColor)
    ) {

//        NonFoundScreen(name = "دارویی")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "داروهای شما", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp)
        ) {

            ExposedDropdownMenuBox(modifier = Modifier.fillMaxWidth(),
                expanded = expandedMenu,
                onExpandedChange = {
                    expandedMenu = it
                }) {

                OutlinedTextField(
                    value = timeShiftName,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMenu) },
                    placeholder = {
                        androidx.compose.material3.Text(
                            text = "لطفا نویت مصرف دارو را انتخاب کنید",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    textStyle = MaterialTheme.typography.titleMedium
                )

                ExposedDropdownMenu(modifier = Modifier.fillMaxWidth(),
                    expanded = expandedMenu,
                    onDismissRequest = { expandedMenu = false }) {

                    DropdownMenuItem(modifier = Modifier.fillMaxWidth(), onClick = {
                        sharedViewModel.timeShiftName.value = "نوبت صبح"
                        sharedViewModel.shiftCode.value = 1
                        expandedMenu = false
                    }) {
                        androidx.compose.material3.Text(
                            text = "نوبت صبح",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )

                    }
                    DropdownMenuItem(onClick = {
                        sharedViewModel.timeShiftName.value = "نوبت ظهر"
                        sharedViewModel.shiftCode.value = 2
                        expandedMenu = false
                    }) {
                        androidx.compose.material3.Text(
                            text = "نوبت ظهر",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )

                    }
                    DropdownMenuItem(onClick = {
                        sharedViewModel.timeShiftName.value = "نوبت عصر"
                        sharedViewModel.shiftCode.value = 3
                        expandedMenu = false
                    }) {
                        androidx.compose.material3.Text(
                            text = "نوبت عصر",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )

                    }
                    DropdownMenuItem(onClick = {
                        sharedViewModel.timeShiftName.value = "نوبت شب"
                        sharedViewModel.shiftCode.value = 4
                        expandedMenu = false
                    }) {
                        androidx.compose.material3.Text(
                            text = "نوبت شب",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }

            }
        }

        LazyColumn {

            if (drugs is RequestState.Success) {

                itemsIndexed((drugs as RequestState.Success<List<DrugsClass>>).data) { index, drugs ->

                    val itemNumber = index + 1

                DrugHomeItem(drugs,itemNumber = itemNumber)

                }
            }
        }
    }
}