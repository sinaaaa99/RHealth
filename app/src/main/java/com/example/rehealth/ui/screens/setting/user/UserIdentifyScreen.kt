package com.example.rehealth.ui.screens.setting.user

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.UserIdentification
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserIdentifyScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    val name by sharedViewModel.userName
    val age by sharedViewModel.userAge
    val problem by sharedViewModel.userProblem

    val userIdentify by sharedViewModel.userIdentify.collectAsState()

    LaunchedEffect(key1 = Unit ){

        sharedViewModel.readUserIdentify()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(associationBackground)
            .verticalScroll(rememberScrollState())
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
                text = "مشخصات بیمار", style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp), contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.pic_profile),
                contentDescription = "picture profile"
            )
        }

        if (userIdentify is RequestState.Success) {

            val userIdentification = userIdentify as RequestState.Success<UserIdentification?>

            if (userIdentification.data!=null){
                LaunchedEffect(key1 = Unit ){
                    sharedViewModel.userName.value = userIdentification.data.name
                    sharedViewModel.userAge.value = userIdentification.data.age
                    sharedViewModel.userProblem.value = userIdentification.data.problem

                }


            }

            //name Text Filed
            OutlinedTextField(
                value = name,
                onValueChange = { sharedViewModel.userName.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = {
                    Text(
                        text = "نام بیمار را وارد کنید",
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

            //Age Text Filed
            OutlinedTextField(
                value = age,
                onValueChange = { sharedViewModel.userAge.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = {
                    Text(
                        text = "سن بیمار را وارد کنید",
                        textAlign = TextAlign.Right,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Number
                ),
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = true
            )

            //Problem Text Filed
            OutlinedTextField(
                value = problem,
                onValueChange = { sharedViewModel.userProblem.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                placeholder = {
                    Text(
                        text = "شرح حال ، مشکل ، بیماری ...",
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


            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Button(modifier = Modifier
                    .width(100.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                    onClick = {


                        sharedViewModel.insertUserIdentify()

                        navHostController.popBackStack()


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
}
