package com.example.rehealth.ui.screens.main.home.quiz

import android.app.NotificationManager
import android.content.Context
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.quiz.QuizAccess
import com.example.rehealth.ui.theme.drugBackgroundColor
import com.example.rehealth.ui.theme.quizCardColorA
import com.example.rehealth.ui.theme.quizCardColorB
import com.example.rehealth.ui.theme.quizIconColorA
import com.example.rehealth.ui.theme.quizIconColorB
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun QuizHomeScreen(navHostController: NavHostController, sharedViewModel: SharedViewModel) {

    val context = LocalContext.current

    val notificationManger =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    notificationManger.cancel(sharedViewModel.quizAlarmId.value)

    var isQuizAccess1 by remember {
        mutableStateOf(true)
    }
    var isQuizAccess2 by remember {
        mutableStateOf(true)
    }
    val dateNow =
        LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()

    LaunchedEffect(key1 = Unit) {
        sharedViewModel.getQuizAccess()
    }
    val requestQuizAccess by sharedViewModel.quizAccess.collectAsState()

    if (requestQuizAccess is RequestState.Success) {

        val quizAccess = requestQuizAccess as RequestState.Success<List<QuizAccess?>>

        if (quizAccess.data.isNotEmpty()) {

            if (quizAccess.data[0] != null) {

                quizAccess.data[0]?.let { quiz1 ->

                    val lastTake1 =
                        quiz1.lastTake.atZone(ZoneId.systemDefault()).toEpochSecond()

                    val diff = dateNow.minus(lastTake1).toDouble()

                    val ringCountCalculate1 = diff / 60 / 60 / 24

                    if (ringCountCalculate1 < 1) {
                        isQuizAccess1 = false
                    }
                }
            }

            if (quizAccess.data.size > 1 && quizAccess.data[1] != null) {

                quizAccess.data[1]?.let { quiz2 ->

                    val lastTake2 =
                        quiz2.lastTake.atZone(ZoneId.systemDefault()).toEpochSecond()


                    val diff = dateNow.minus(lastTake2).toDouble()

                    val ringCountCalculate2 = diff / 60 / 60 / 24

                    if (ringCountCalculate2 < 1) {
                        isQuizAccess2 = false
                    }
                }

            }

        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(drugBackgroundColor)
    ) {

//        DoneScreen()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                modifier = Modifier.clickable { navHostController.popBackStack() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft quiz screen"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "سوالات روزانه",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(8.dp))

        QuizMenu(cardColor = quizCardColorA, iconColor = quizIconColorA, "سوالات سری اول") {

            //on click listener

            if (isQuizAccess1)
                navHostController.navigate("QuestionsScreenRoute/1")
            else
                Toast.makeText(
                    context,
                    "شما امروز به سوالات سری اول پاسخ داده اید.",
                    Toast.LENGTH_SHORT
                ).show()

        }

        QuizMenu(cardColor = quizCardColorB, iconColor = quizIconColorB, "سوالات سری دوم") {

            //on click listener
            if (isQuizAccess2)
                navHostController.navigate("QuestionsScreenRoute/2")
            else
                Toast.makeText(
                    context,
                    "شما امروز به سوالات سری دوم پاسخ داده اید.",
                    Toast.LENGTH_SHORT
                ).show()
        }


    }
}


@Composable
fun QuizMenu(cardColor: Color, iconColor: Color, title: String, onArrowClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {


            Column(
                verticalArrangement = Arrangement.Bottom
            ) {

                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(
                            Color.White
                        )
                        .clickable { onArrowClick() }, contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "start quiz icon"
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .background(color = iconColor)
                            .padding(4.dp), contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "quiz_test_icon"
                        )
                    }

                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = "7 سوال",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(id = R.drawable.ic_pen),
                        contentDescription = "icon pen"
                    )
                }
            }

        }

    }
}

@Composable
@Preview
fun QuizHomeScreenPreview() {
//    QuizHomeScreen()
}