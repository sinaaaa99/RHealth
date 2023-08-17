package com.example.rehealth.ui.screens.setting


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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.AlarmScheduler
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.theme.yellow10
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.clock.ClockDialog
import com.maxkeppeler.sheets.clock.models.ClockConfig
import com.maxkeppeler.sheets.clock.models.ClockSelection
import java.time.LocalDate
import java.time.LocalTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitOption(alarmSchedule: AlarmScheduler) {

    //alarm
    var visitReminder: VisitReminder?

    val scrollable = rememberScrollState()

    var doctorName by remember {
        mutableStateOf("")
    }

    var alarmCheek by remember {
        mutableStateOf(true)
    }


    //calendar creator
    val calenderState = rememberSheetState()
    var selectedDate by remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }

    CalendarDialog(
        state = calenderState,
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
            maxYear = 2028,
            minYear = 2023
        ),
        selection = CalendarSelection.Date { date ->
            selectedDate = date

            Log.d("calenderTime", selectedDate.toString())
        })

    //clock creator
    val clockState = rememberSheetState()
    var selectedTime by remember {
        mutableStateOf<LocalTime>(LocalTime.now())
    }

    ClockDialog(
        state = clockState,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->
            selectedTime = LocalTime.of(h, m)
        })




    Column(
        Modifier
            .fillMaxSize()
            .background(color = buttonColor)
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
            text = "ایجاد ویزیت جدید",
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

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "نام پزشک",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Right
                )

                OutlinedTextField(
                    value = doctorName,
                    onValueChange = { doctorName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    placeholder = {
                        Text(
                            text = "نام پزشک مربوطه را وارد کنید",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    textStyle = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                CalenderStyle(selectedDate) {
                    calenderState.show()
                }



                Spacer(modifier = Modifier.height(16.dp))

                ClockStyle(selectedTime) {
                    clockState.show()
                }


                Spacer(modifier = Modifier.height(32.dp))


                SaveItems(alarmCheek, ({ onCheek ->
                    alarmCheek = onCheek
                })) {

                    val time = selectedTime.atDate(selectedDate)
                    val id = (1..100000000).random()



                    Log.d("alarmTimeSet",time.toString())

                    visitReminder = VisitReminder(
                        id, time,
                        doctorName, doctorName
                    )


                    visitReminder?.let(alarmSchedule::schedule)
                }


            }


        }
    }
}


@Composable
fun CalenderStyle(selectedDate: LocalDate, onCalendarClick: () -> Unit) {
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
                modifier = Modifier.clickable {
                    onCalendarClick()
                },
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "ic_calendar visit option"
            )
        }

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "تاریخ",
            style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Right
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Gray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = selectedDate.toString(), modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun ClockStyle(selectedTime: LocalTime, onClockClick: () -> Unit) {
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
                modifier = Modifier.clickable {
                    onClockClick()
                },
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "ic_calendar visit option"
            )
        }

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "ساعت",
            style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Right
        )


    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 16.dp), horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            Text(text = "ساعت", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "دقیقه", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .width(100.dp)
                .clickable { onClockClick() },
            colors = CardDefaults.cardColors(containerColor = buttonColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp, vertical = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = selectedTime.hour.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Text(
                        text = selectedTime.minute.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(6.dp))

                    Icon(
                        modifier = Modifier.size(10.dp),
                        painter = painterResource(id = R.drawable.ic_arrow),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    }

}

@Composable
fun SaveItems(alarmCheek: Boolean, onAlarmClick: (Boolean) -> Unit, onSaveClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Alarm", style = MaterialTheme.typography.titleLarge)
            Switch(
                checked = alarmCheek,
                onCheckedChange = { onCheek ->
                    onAlarmClick(onCheek)

                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = buttonColor,
                    checkedThumbColor = Color.White
                )
            )
        }

        Button(
            onClick = { onSaveClick() },
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(16.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)
        ) {
            Text(
                text = "ذخیره",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


    }

}

@Composable
@Preview(showBackground = true)
fun CalenderShow() {
//    VisitOption()
}