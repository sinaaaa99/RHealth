package com.example.rehealth.ui.screens.setting.drugs

import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.data.models.drug.ReminderWithDrugs
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.theme.greenCheck
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateDrugsScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    val drugId by sharedViewModel.reminderId

    Log.d("drugId", drugId.toString())

    LaunchedEffect(Unit) {
        sharedViewModel.getReminderWithDrugs()
    }

    val reminderWithDrugs by sharedViewModel.reminderWithDrugs.collectAsState()

    val drugName by sharedViewModel.drugName
    val drugsNumber by sharedViewModel.drugsNumber

    val drugAdvice by sharedViewModel.drugAdvice

    val context = LocalContext.current


    if (reminderWithDrugs is RequestState.Success) {

        val reminderAndDrugs = reminderWithDrugs as RequestState.Success<ReminderWithDrugs>

        sharedViewModel.shiftCode.value = reminderAndDrugs.data.reminder.shiftCode


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(associationBackground)
        ) {

            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .clickable { navHostController.popBackStack() }
                    .background(color = green30)
                    .padding(10.dp)
            ) {

                Row {

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "visit option close icon"
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = "ذخیره", style = MaterialTheme.typography.bodyLarge)
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = reminderAndDrugs.data.reminder.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {

                    AddTextFiled(
                        text = drugName,
                        drugsNumber = drugsNumber,
                        onNumberChange = { numberSelectedByUser ->
                            sharedViewModel.drugsNumber.value = numberSelectedByUser
                        },
                        onValueChange = { newText ->
                            sharedViewModel.drugName.value = newText
                        }
                    )

                    //advice screen
                    OutlinedTextField(
                        value = drugAdvice,
                        onValueChange = { sharedViewModel.drugAdvice.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        placeholder = {
                            Text(
                                text = "توصیه مربوط به دارو را وارد کنید",
                                textAlign = TextAlign.Right,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge,
                    )

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        Button(modifier = Modifier
                            .padding(12.dp)
                            .clip(RoundedCornerShape(16.dp)),
                            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                            onClick = {


                                if (drugName.trim().isNotBlank())
                                    sharedViewModel.insertDrugs()
                                else
                                    Toast.makeText(
                                        context,
                                        "لطفا نام دارو را وارد کنید.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                            })
                        {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                Text(
                                    text = "اضافه کردن",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add drug button icon",
                                    tint = greenCheck
                                )
                            }


                        }
                    }


                    Spacer(modifier = Modifier.height(12.dp))

                    LazyColumn {

                        items(reminderAndDrugs.data.drugs) { drug ->

                            DrugItems(drugName = drug.drugName) {

                                sharedViewModel.eachDrugId.value = drug.drugId
                                sharedViewModel.deleteDrug()
                            }
                        }
                    }
                }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTextFiled(
    text: String,
    drugsNumber: String,
    onNumberChange: (String) -> Unit,
    onValueChange: (String) -> Unit
) {

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = "نام دارو",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Right
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {


        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f),
            placeholder = {
                Text(
                    text = "نام دارو را وارد کنید",
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Text
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.weight(2f),
            value = drugsNumber,
            onValueChange = { onNumberChange(it) },
            placeholder = {
                Text(
                    text = "دوز",
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
            ), textStyle = MaterialTheme.typography.bodyLarge, singleLine = true
        )
    }
}

@Composable
fun DrugItems(drugName: String, onDeleteClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(green30),
        contentAlignment = Alignment.Center
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp)
        ) {

            Icon(
                modifier = Modifier.clickable { onDeleteClick() },
                imageVector = Icons.Default.Close,
                contentDescription = "icon delete a drug form drug class"
            )

            Text(
                text = drugName,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


    }
}