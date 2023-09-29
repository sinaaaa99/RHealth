package com.example.rehealth.ui.screens.main.home.quiz


import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rehealth.R
import com.example.rehealth.data.models.quiz.QuizClass
import com.example.rehealth.data.models.quiz.QuizResult
import com.example.rehealth.navigation.routes.Routes.QuestionDoneScreenRoute
import com.example.rehealth.navigation.routes.Routes.QuestionsScreenRoute
import com.example.rehealth.ui.theme.backgroundColorQuestion
import com.example.rehealth.ui.theme.backgroundColorQuiz
import com.example.rehealth.ui.theme.buttonColorQuiz
import com.example.rehealth.ui.theme.greenCheck
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState
import java.time.LocalDateTime

@Composable
fun ExamScreen(sharedViewModel: SharedViewModel, navHostController: NavHostController) {


    val quizType by sharedViewModel.quizType

    val currentQuestion by
    if (quizType == 1) sharedViewModel.currentQuestion1
    else sharedViewModel.currentQuestion2


    var isSelected1 by remember(currentQuestion) {
        mutableStateOf(false)
    }
    var isSelected2 by remember(currentQuestion) {
        mutableStateOf(false)
    }


    val questions by sharedViewModel.allQuiz.collectAsState()


    LaunchedEffect(key1 = quizType) {

        sharedViewModel.getQuiz()

        sharedViewModel.getUserCheeks()

    }
    val usersCheeks by sharedViewModel.userCheeks.collectAsState()


    if (usersCheeks is RequestState.Success) {

        val userCheeksResult = usersCheeks as RequestState.Success<QuizResult>


        sharedViewModel.userCheeks1.value = userCheeksResult.data.userCheek1

        sharedViewModel.userCheeks2.value = userCheeksResult.data.userCheek2

    }

    var userCheeksYes by remember(quizType) {
        mutableStateOf(0)
    }


    val context = LocalContext.current


    val scrollable = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColorQuiz)
            .verticalScroll(scrollable)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "KeyboardArrowLeft quiz screen"
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (quizType == 1) "سوالات سری اول" else "سوالات سری دوم",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

        }



        Spacer(modifier = Modifier.height(16.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Image(
                modifier = Modifier.size(44.dp),
                painter = painterResource(id = R.drawable.ic_exam),
                contentDescription = "Clock icon exam"
            )


            Text(
                text = "سوال ${currentQuestion + 1} / 7",
                style = MaterialTheme.typography.titleMedium
            )

        }


        Spacer(modifier = Modifier.height(8.dp))


        if (questions is RequestState.Success) {

            val question = questions as RequestState.Success<List<QuizClass>>



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(12.dp),
                colors = CardDefaults.cardColors(containerColor = backgroundColorQuestion)
            ) {

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center,
                        text = question.data[currentQuestion].title,
                        style = MaterialTheme.typography.titleMedium.copy(lineHeight = 32.sp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,

                        )


                }

            }


            Spacer(modifier = Modifier.height(8.dp))

            OptionsBox(isSelected = isSelected1, 1, question.data[currentQuestion].answerText1) {

                isSelected1 = !isSelected1


                isSelected2 = false

            }

            OptionsBox(isSelected = isSelected2, 2, question.data[currentQuestion].answerText2) {


                isSelected2 = !isSelected2

                isSelected1 = false
            }


            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                        .height(60.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(buttonColorQuiz)
                        .clickable {

                            if (isSelected1 or isSelected2) {

                                sharedViewModel.quizId.value = question.data[currentQuestion].quizId
                                sharedViewModel.userAnswerRate.value =
                                    if (isSelected1) 1 else if (isSelected2) 2 else 0
                                sharedViewModel.userAnswerDate.value = LocalDateTime.now()

                                //review user yes Answer
                                if (isSelected1) {
                                    userCheeksYes++
                                }

                                sharedViewModel.changeQuestion()

                                if (currentQuestion == 6) {

                                    if (isSelected1){
                                        sharedViewModel.userCheeks1.value +=10
                                        sharedViewModel.updateQuizResult1()
                                    }else if (userCheeksYes >= 3) {

                                        if (quizType == 1) {

                                            sharedViewModel.userCheeks1.value++
                                            sharedViewModel.updateQuizResult1()
                                        } else if (quizType == 2) {
                                            sharedViewModel.userCheeks2.value++
                                            sharedViewModel.updateQuizResult2()
                                        }
                                    } else {
                                        if (quizType == 1) {

                                            sharedViewModel.userCheeks1.value = 0
                                            sharedViewModel.updateQuizResult1()
                                        } else if (quizType == 2) {

                                            sharedViewModel.userCheeks2.value = 0
                                            sharedViewModel.updateQuizResult2()
                                        }


                                    }

                                    navHostController.navigate(QuestionDoneScreenRoute) {
                                        popUpTo(QuestionsScreenRoute) {
                                            inclusive = true
                                        }

                                    }

                                    sharedViewModel.currentQuestion1.value = 0
                                    sharedViewModel.currentQuestion2.value = 0

                                }


                            } else {

                                Toast
                                    .makeText(
                                        context,
                                        "لطفا یکی از گزینه ها را انتخاب کنید",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }

                        }, contentAlignment = Alignment.Center

                ) {

                    Text(
                        text = if (currentQuestion == 6) "پایان" else "بعدی",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


        }


    }
}


@Composable
fun OptionsBox(isSelected: Boolean, answerNumber: Int, answerText: String, onBoxClick: () -> Unit) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(60.dp)
            .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(if (isSelected) greenCheck else MaterialTheme.colorScheme.background)
            .clickable {

                onBoxClick()

            }
            .padding(12.dp),
    ) {

        if (isSelected) {

            Box(contentAlignment = Alignment.CenterStart, modifier = Modifier.size(30.dp)) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    contentDescription = "icon cheek",
                    tint = Color.White
                )
            }
        }

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Text(
                color = if (isSelected) Color.White else Color.Black,
                text = answerText,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold
            )

            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight(),
                color = if (isSelected) Color.White else Color.Gray
            )

            Text(
                color = if (isSelected) Color.White else Color.Black,
                text = answerNumber.toString(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(horizontal = 12.dp)
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun ExamScreenPreview() {

//    ExamScreen(sharedViewModel = viewModel())
}