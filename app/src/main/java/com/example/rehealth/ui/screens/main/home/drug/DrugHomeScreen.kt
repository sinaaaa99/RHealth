package com.example.rehealth.ui.screens.main.home.drug

import android.app.NotificationManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.drug.ReminderWithDrugs
import com.example.rehealth.ui.theme.drugBackgroundColor
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrugHomeScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    LaunchedEffect(Unit) {
        sharedViewModel.shiftCode.value = 0
        sharedViewModel.timeShiftName.value = ""
    }


    val context = LocalContext.current

    val notificationManger =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManger.cancel(sharedViewModel.drugAlarmId.value)

    val timeShiftName by sharedViewModel.timeShiftName

    val shiftCode by sharedViewModel.shiftCode

    var expandedMenu by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(drugBackgroundColor)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {


            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navHostController.popBackStack() },
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
                        Text(
                            text = "لطفا نوبت مصرف دارو را انتخاب کنید",
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
                        Text(
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
                        Text(
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
                        Text(
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
                        Text(
                            text = "نوبت شب",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }

            }
        }


        if (shiftCode == 0) {

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_point_top),
                    contentDescription = "icon point top"
                )
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "لطفا از منوی بالا یکی از نوبت ها را انتخاب کنید.",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        } else {

            val reminderWithDrugsByShift by sharedViewModel.reminderWithDrugsByShift.collectAsState()

            LaunchedEffect(shiftCode) {

                sharedViewModel.getReminderWithDrugsByShift()

            }

            if (reminderWithDrugsByShift is RequestState.Success) {

                val drugs =
                    (reminderWithDrugsByShift as RequestState.Success<ReminderWithDrugs?>).data

                if (drugs != null) {

                    if (drugs.drugs.isEmpty()) {

                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_no_drug),
                                contentDescription = "icon no drug"
                            )
                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "برای این نوبت دارویی ندارید.",
                                style = MaterialTheme.typography.titleMedium
                            )

                        }
                    } else {

                        LazyColumn {

                            itemsIndexed(drugs.drugs) { index, data ->

                                DrugHomeItem(data, drugs.reminder, index + 1)

                            }
                        }

                    }
                } else {
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_no_drug),
                            contentDescription = "icon no drug"
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "این نوبت تعیین نشده است.",
                            style = MaterialTheme.typography.titleMedium
                        )

                    }
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    CircularProgressIndicator()

                }
            }
        }


        /*else if (reminderWithDrugsByShift is RequestState.Error) {

            val error = reminderWithDrugsByShift as RequestState.Error

            Text(text = "Error ${error.error}")
        }*/
    }
}