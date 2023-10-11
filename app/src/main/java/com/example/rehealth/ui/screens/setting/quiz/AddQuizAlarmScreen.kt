package com.example.rehealth.ui.screens.setting.quiz

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.interfaces.QuizScheduler
import com.example.rehealth.data.models.QuizReminder
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddQuizAlarmScreen(
    quizScheduler: QuizScheduler,
    navHostController: NavHostController,
    sharedViewModel: SharedViewModel
) {

    //clock creator
    val clockState = rememberSheetState()
    val selectedTime by sharedViewModel.quizTimeReminder

    ClockDialog(
        state = clockState,
        config = ClockConfig(is24HourFormat = true),
        selection = ClockSelection.HoursMinutes { h, m ->

            sharedViewModel.quizTimeReminder.value =
                LocalTime.of(h, m).atDate(LocalDate.now())


        })

    var alarmCheek by remember {
        mutableStateOf(true)
    }

    val scrollable = rememberScrollState()

    val quizAlarmName by sharedViewModel.quizAlarmName

    var quizReminder: QuizReminder?

    val quizId by sharedViewModel.quizId
    val quizAlarmId by sharedViewModel.quizAlarmId

    val context = LocalContext.current

    val notificationPermissionState = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {}
    )

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
                .clickable {
                    navHostController.popBackStack()
                }
        ) {

            Row(modifier = Modifier.clickable { navHostController.popBackStack() }) {

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
            text = "ایجاد آلارم جدید",
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
                    text = "نام آلارم",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Right
                )

                OutlinedTextField(
                    value = quizAlarmName,
                    onValueChange = { sharedViewModel.quizAlarmName.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    placeholder = {
                        Text(
                            text = "نام آلارم مربوطه را وارد کنید",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    textStyle = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                QuizClockStyle(selectedTime) {
                    clockState.show()
                }


                Spacer(modifier = Modifier.height(32.dp))


                QuizSaveItems(alarmCheek, ({ onCheek ->
                    alarmCheek = onCheek
                })) {


                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {

                        val permissionCheekResult =
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.POST_NOTIFICATIONS
                            )

                        if (permissionCheekResult == PackageManager.PERMISSION_GRANTED) {

                            quizReminder =
                                QuizReminder(quizId, quizAlarmId, quizAlarmName, selectedTime)

                            quizScheduler.schedule(quizReminder!!)

                            sharedViewModel.insertQuizReminder()

                            navHostController.popBackStack()
                        } else {
                            notificationPermissionState.launch(Manifest.permission.POST_NOTIFICATIONS)
                        }


                    }
                    else {

                        quizReminder =
                            QuizReminder(quizId, quizAlarmId, quizAlarmName, selectedTime)

                        quizScheduler.schedule(quizReminder!!)

                        sharedViewModel.insertQuizReminder()

                        navHostController.popBackStack()

                    }

                }


            }


        }
    }
}

@Composable
fun QuizClockStyle(selectedTime: LocalDateTime, onClockClick: () -> Unit) {
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
fun QuizSaveItems(alarmCheek: Boolean, onAlarmClick: (Boolean) -> Unit, onSaveClick: () -> Unit) {

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