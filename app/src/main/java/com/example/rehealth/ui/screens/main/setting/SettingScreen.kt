package com.example.rehealth.ui.screens.main.setting

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.LockClass
import com.example.rehealth.data.models.UserIdentification
import com.example.rehealth.navigation.routes.Routes.DrugListScreenRoute
import com.example.rehealth.navigation.routes.Routes.TestListScreenRoute
import com.example.rehealth.navigation.routes.Routes.VisitListScreenRoute
import com.example.rehealth.ui.theme.associationBackground
import com.example.rehealth.ui.theme.buttonColor
import com.example.rehealth.ui.theme.cream10
import com.example.rehealth.ui.theme.drugSettingBack
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.theme.quizSettingBack
import com.example.rehealth.ui.theme.testSettingBack
import com.example.rehealth.ui.theme.visitSettingBack
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    val context = LocalContext.current


    var isLock by sharedViewModel.isScreenLock

    LaunchedEffect(key1 = Unit) {

        sharedViewModel.getPassword()
    }

    var correctPassword by remember {
        mutableStateOf("ReHealthAppVersion1")
    }

    val readPass by sharedViewModel.readLock.collectAsState()

    if (readPass is RequestState.Success) {

        val passwordData = readPass as RequestState.Success<LockClass?>

        if (passwordData.data != null) {
            LaunchedEffect(key1 = Unit) {
                sharedViewModel.isScreenLock.value = passwordData.data.isLock

            }

            correctPassword = passwordData.data.password
        }

    }
    var passwordShown by remember {
        mutableStateOf(false)
    }
    var password by remember {
        mutableStateOf("")
    }

    val userIdentify by sharedViewModel.userIdentify.collectAsState()
    LaunchedEffect(key1 = Unit) {
        sharedViewModel.readUserIdentify()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        AnimatedVisibility(visible = isLock) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(associationBackground)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

                    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 16.dp)) {

                        Text(
                            text = "\u2022  رمز",
                            textAlign = TextAlign.Right,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            placeholder = {
                                Text(
                                    text = "رمز خود را وارد کنید",
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

                                        androidx.compose.material3.Icon(
                                            painter = painterResource(id = R.drawable.ic_disvisibile),
                                            contentDescription = "icon dis visible"
                                        )

                                    }
                                } else {
                                    IconButton(onClick = { passwordShown = true }) {

                                        androidx.compose.material3.Icon(
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

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            Button(modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .padding(24.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
                                onClick = {

                                    if (password == correctPassword) {

                                        isLock = false
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "رمز وارد شده اشتباه است.",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                })
                            {
                                androidx.compose.material3.Text(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    text = "تایید",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                            }

                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {

                            Text(
                                text = "فقط ادمین به تنظیمات دسترسی دارد.",
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Icon(imageVector = Icons.Default.Info, contentDescription = "icon info")

                        }
                    }
                }
            }
        }

        AnimatedVisibility(visible = !isLock) {

            val scrollable = rememberScrollState()


            Scaffold(topBar = {
                SettingTopAppBar(onSettingClick = {

                    navHostController.navigate("LockScreen")
                }, {

                    //on profile Click
                    navHostController.navigate("UserIdentifyScreen")
                }) {
                    //on Reset Click
                    sharedViewModel.userCheeks1.value=0
                    sharedViewModel.userCheeks2.value=0
                    sharedViewModel.updateQuizResult1()
                    sharedViewModel.updateQuizResult2()
                    Toast.makeText(context,"توصیه پزشک به حالت اول برگشت.",Toast.LENGTH_SHORT).show()
                }
            }) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(associationBackground)
                        .padding(it)
                        .verticalScroll(scrollable)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {

                        if (userIdentify is RequestState.Success) {

                            val userData = userIdentify as RequestState.Success<UserIdentification?>
                            if (userData.data != null) {
                                val userName = userData.data.name

                                Text(
                                    text = userName,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }

                        }
                        Text(
                            text = "سلام,", style = MaterialTheme.typography.titleLarge
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(110.dp)
                            .padding(8.dp)
                            .clickable {
                                navHostController.navigate("AssociationMainScreen")
                            }, colors = CardDefaults.cardColors(containerColor = green30)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(color = Color.White)
                            ) {

                                Image(
                                    modifier = Modifier.padding(8.dp),
                                    painter = painterResource(id = R.drawable.pic_setting_info),
                                    contentDescription = ""
                                )
                            }


                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "میزان مشارکت شما",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "پاسخ به هشدار ها",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = cream10
                                )
                            }


                        }


                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, end = 10.dp, start = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.setting_service),
                            contentDescription = ""
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(
                                modifier = Modifier.size(30.dp),
                                painter = painterResource(id = R.drawable.bottom_arrow),
                                contentDescription = ""
                            )

                            Text(
                                text = "خدمات",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(modifier = Modifier.weight(1f)) {

                            SettingItems(
                                image = R.drawable.pic_visit,
                                title = "ویزیت",
                                bacColor = visitSettingBack,
                                height = 200.dp
                            ) {

                                navHostController.navigate(VisitListScreenRoute)

                            }

                            SettingItems(
                                image = R.drawable.pic_test_set,
                                title = "آزمایش",
                                bacColor = testSettingBack,
                                height = 250.dp
                            ) {

                                navHostController.navigate(TestListScreenRoute)

                            }

                        }

                        Column(modifier = Modifier.weight(1f)) {

                            SettingItems(
                                image = R.drawable.pic_drug_set,
                                title = "دارو",
                                bacColor = drugSettingBack,
                                height = 250.dp
                            ) {

                                navHostController.navigate(DrugListScreenRoute)

                            }

                            SettingItems(
                                image = R.drawable.pic_quiz,
                                title = "پرسشنامه",
                                bacColor = quizSettingBack,
                                height = 200.dp
                            ) {

                                navHostController.navigate("ListQuizReminderScreen")
                            }

                        }
                    }
                }

            }
        }
    }

}