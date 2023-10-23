package com.example.rehealth.ui.screens.setting.lock

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.viewmodel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddLockScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    val password by sharedViewModel.password

    var passwordShown by remember {
        mutableStateOf(false)
    }

    val isLock by sharedViewModel.isOpen


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(associationBackground)
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
                text = "ایجاد رمز جدید", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        Spacer(modifier = Modifier.height(16.dp))


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Column(modifier = Modifier
                .padding(12.dp)) {

                OutlinedTextField(
                    value = password,
                    onValueChange = { sharedViewModel.password.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = {
                        Text(
                            text = "رمز جدید را وارد کنید",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                    ),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    singleLine = true,
                    leadingIcon = {

                        if (passwordShown) {
                            IconButton(onClick = { passwordShown = false }) {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_disvisibile),
                                    contentDescription = "icon dis visible"
                                )

                            }
                        } else {
                            IconButton(onClick = { passwordShown = true }) {

                                Icon(
                                    painter = painterResource(id = R.drawable.ic_visibile),
                                    contentDescription = "icon  visible"
                                )

                            }
                        }
                    },
                    visualTransformation = if (passwordShown) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    }
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Switch(
                        checked = isLock, onCheckedChange = { sharedViewModel.isOpen.value = it },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = buttonColor,
                            checkedThumbColor = Color.White

                        )
                    )

                    Image(
                        painter = painterResource(
                            id = if (isLock) R.drawable.ic_padlock else R.drawable.ic_unlocked
                        ), contentDescription = "icon lock and un lock"
                    )

                }

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(24.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                        onClick = {

                            sharedViewModel.insertLock()

                            navHostController.popBackStack()


                        })
                    {
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
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