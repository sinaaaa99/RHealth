package com.example.rehealth.ui.screens.setting.drugs

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.DrugScheduler
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.ui.theme.yellow10
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDrugScreen(sharedViewModel: SharedViewModel, drugScheduler: DrugScheduler) {

    var drugReminder: DrugReminder?

    //clock creator1
    val clockState1 = rememberSheetState()
    var selectedTime1 by remember {
        mutableStateOf<LocalTime>(LocalTime.now())
    }
    ClockDialog(
        state = clockState1,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            selectedTime1 = LocalTime.of(h, m)

        })

    //clock creator2
    val clockState2 = rememberSheetState()
    var selectedTime2 by remember {
        mutableStateOf<LocalTime?>(null)
    }
    ClockDialog(
        state = clockState2,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            selectedTime2 = LocalTime.of(h, m)

        })

    //clock creator3
    val clockState3 = rememberSheetState()
    var selectedTime3 by remember {
        mutableStateOf<LocalTime?>(null)
    }
    ClockDialog(
        state = clockState3,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            selectedTime3 = LocalTime.of(h, m)

        })

    //clock creator4
    val clockState4 = rememberSheetState()
    var selectedTime4 by remember {
        mutableStateOf<LocalTime?>(null)
    }
    ClockDialog(
        state = clockState4,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            selectedTime4 = LocalTime.of(h, m)

        })

    var drugName by remember {
        mutableStateOf("")
    }
    var drugDooz by remember {
        mutableStateOf("")
    }

    val scrollable = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .background(color = yellow10)
                .padding(10.dp)
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
            style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Right,
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

                CustomTextFiled(
                    drugName, "نام دارو", "نام دارو را وارد کنید",
                    KeyboardType.Text
                ) { newText ->
                    drugName = newText

                }

                Spacer(modifier = Modifier.height(16.dp))

                CustomTextFiled(
                    drugDooz, "دوز دارو", "دوز دارو را وارد کنید",
                    KeyboardType.Number
                ) { newText ->
                    drugDooz = newText

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
                            .size(40.dp), contentAlignment = Alignment.Center
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "ic_calendar visit option"
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(10.dp),
                        text = "یادآورها",
                        style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Right
                    )
                }

                CustomReminderStyle(selectedTime1) {
                    clockState1.show()
                }
                CustomReminderStyle(selectedTime2) {
                    clockState2.show()
                }
                CustomReminderStyle(selectedTime3) {
                    clockState3.show()
                }
                CustomReminderStyle(selectedTime4) {
                    clockState4.show()
                }


                Button(onClick = {

                    val id = (1..100000000).random()


                    val time = selectedTime1.atDate(LocalDate.now())

                    Log.d("alarmTimeSet", time.toString())

                    drugReminder = DrugReminder(id, drugName, drugDooz, time)


//                    sharedViewModel.insertDrugReminder(drugReminder)



                    drugScheduler.schedule(drugReminder!!)


                }) {
                    Text(text = "save")

                }
            }
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextFiled(
    text: String,
    title: String,
    placeHolder: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = title,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Right
    )

    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        placeholder = {
            Text(
                text = placeHolder,
                textAlign = TextAlign.Right,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelSmall
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = keyboardType),
        textStyle = MaterialTheme.typography.bodyLarge
    )


}

@Composable
fun CustomReminderStyle(selectedTime: LocalTime?, onCalendarClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .border(width = 1.dp, color = Color.Gray, RoundedCornerShape(8.dp))
            .clickable {
                onCalendarClick()
            },
        contentAlignment = Alignment.Center
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