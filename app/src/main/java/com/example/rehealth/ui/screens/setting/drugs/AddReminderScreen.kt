package com.example.rehealth.ui.screens.setting.drugs

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.theme.yellow10
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReminderScreen(
    sharedViewModel: SharedViewModel, drugScheduler: DrugScheduler, navController: NavHostController
) {

    val context = LocalContext.current

    var drugReminder: DrugReminder?


    //clock creator
    val clockState = rememberSheetState()
    val selectedTime by sharedViewModel.timeReminder
    ClockDialog(state = clockState,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            sharedViewModel.timeReminder.value = LocalTime.of(h, m).atDate(LocalDate.now())

        })

    LaunchedEffect(key1 = Unit) {

        sharedViewModel.reminderId.value = UUID.randomUUID()
        sharedViewModel.alarmId.value = (1..2000000000).random()

    }
    val timeShiftName by sharedViewModel.timeShiftName

    val reminderId by sharedViewModel.reminderId
    val alarmId by sharedViewModel.alarmId


    var expandedMenu by remember {
        mutableStateOf(false)
    }

    val timeShiftCode by sharedViewModel.shiftCode

    val scrollable = rememberScrollState()

    val notificationPermissionState = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {}
    )



    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .background(color = yellow10)
                .padding(10.dp)
                .clickable { navController.popBackStack() }
        ) {

            Row {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "visit option close icon"
                )

                Text(text = "برگشت", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "ایجاد داروی جدید",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollable)
                    .padding(top = 16.dp)
            ) {


                Spacer(modifier = Modifier.height(16.dp))

                //Drop down Menu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
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


                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Image(
                            modifier = Modifier.clickable { clockState.show() },
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "ic_calendar visit option"
                        )
                    }

                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = "یادآور",
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Right
                    )
                }

                CustomReminderStyle(selectedTime) {
                    clockState.show()
                }


                Spacer(modifier = Modifier.height(16.dp))

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    Button(modifier = Modifier
                        .width(100.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(16.dp)),
                        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                        onClick = {

                            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {

                                val permissionCheekResult =
                                    ContextCompat.checkSelfPermission(
                                        context,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    )

                                if (permissionCheekResult == PackageManager.PERMISSION_GRANTED) {
                                    drugReminder =
                                        DrugReminder(
                                            reminderId,
                                            alarmId,
                                            timeShiftName,
                                            selectedTime,
                                            timeShiftCode,
                                            0
                                        )

                                    drugScheduler.schedule(drugReminder!!)

                                    sharedViewModel.insertDrugReminder()

                                    navController.popBackStack()

                                } else
                                    notificationPermissionState.launch(Manifest.permission.POST_NOTIFICATIONS)

                            } else {

                                drugReminder =
                                    DrugReminder(
                                        reminderId,
                                        alarmId,
                                        timeShiftName,
                                        selectedTime,
                                        timeShiftCode,
                                        0
                                    )

                                drugScheduler.schedule(drugReminder!!)
//                              drugReminder?.let { drugScheduler::schedule }

                                sharedViewModel.insertDrugReminder()

                                navController.popBackStack()
                            }


                        })
                    {
                        Text(
                            text = "ذخیره",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }
        }

    }

}

@Composable
fun CustomReminderStyle(selectedTime: LocalDateTime?, onCalendarClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(width = 1.dp, color = Color.Gray, RoundedCornerShape(8.dp))
            .clickable {
                onCalendarClick()
            }, contentAlignment = Alignment.Center
    ) {
        if (selectedTime == null) {

            Icon(
                modifier = Modifier.padding(vertical = 4.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "drug clock reminder add icon "
            )


        } else {

            Text(
                text = selectedTime.format(DateTimeFormatter.ofPattern("hh:mm - a")),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
@Preview
fun AddDrugScreenShow() {

//    AddDrugScreen()
}