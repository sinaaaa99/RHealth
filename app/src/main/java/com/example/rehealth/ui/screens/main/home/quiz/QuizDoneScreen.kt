package com.example.rehealth.ui.screens.main.home.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.quiz.QuizResult
import com.example.rehealth.navigation.routes.Routes
import com.example.rehealth.ui.theme.CardColorDone
import com.example.rehealth.ui.theme.backgroundColorDone
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState
import java.time.LocalDateTime

@Composable
fun DoneScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {

    val quizType by sharedViewModel.quizType

    val usersCheeks by sharedViewModel.userCheeks.collectAsState()

    var patientState by remember {
        mutableStateOf(0)
    }


    if (usersCheeks is RequestState.Success) {

        val userCheeksResult = usersCheeks as RequestState.Success<QuizResult>

        if (userCheeksResult.data.userCheek1 > 6 || userCheeksResult.data.userCheek2 > 6) {

            patientState = 1
        }


    }
    LaunchedEffect(key1 = Unit) {

        sharedViewModel.quizAccessId.value = quizType
        sharedViewModel.quizTakeTime.value = LocalDateTime.now()
        sharedViewModel.insertQuizAccess()
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight(0.45f)
                .fillMaxWidth()
                .clip(AbsoluteRoundedCornerShape(bottomLeft = 150.dp))
                .background(
                    backgroundColorDone
                )
        ) {

            val quizSeries = if (quizType == 1) "سوالات سری اول" else "سوالات سری دوم"

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Icon(
                    modifier = Modifier.clickable { navHostController.popBackStack() },
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "KeyboardArrowLeft quiz screen"
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = quizSeries,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )

            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                Image(
                    painter = painterResource(id = R.drawable.pic_done),
                    contentDescription = "done questions"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    text = "شما به همه $quizSeries امروز پاسخ داده اید.",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "توصیه پزشک",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold, color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = CardColorDone),
            shape = RoundedCornerShape(
                topStart = 40.dp,
                topEnd = 0.dp,
                bottomEnd = 40.dp,
                bottomStart = 40.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(8f)) {


                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = if (patientState == 0) "لطفا داروهای خود را به موقع مصرف نمایید "
                        else "هرچه سریعتر به پزشک خود مراجعه نمایید.",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(2f)
                        .padding(8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.pic_dr_advice),
                        contentDescription = "pic doctor advice home screen"
                    )
                }

            }
        }

        if (quizType == 1) {

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = green30, RoundedCornerShape(16.dp))
                        .clickable {
                            navHostController.navigate("QuestionsScreenRoute/2") {
                                popUpTo(Routes.QuizHomeScreenRoute) {
                                    inclusive = true
                                }
                            }
                        }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Text(text = "ادامه سوالات", style = MaterialTheme.typography.titleMedium)

                }

            }
        }


    }
}

@Composable
@Preview(showBackground = true)
fun DoneScreenPreview() {

//    DoneScreen()
}