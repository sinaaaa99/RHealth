package com.example.rehealth.ui.screens.main.home.quiz

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
import com.example.rehealth.navigation.routes.Routes.QuestionsScreenRoute
import com.example.rehealth.ui.theme.green20
import com.example.rehealth.ui.theme.menuItemColor2
import com.example.rehealth.ui.theme.menuItemColor4
import com.example.rehealth.ui.theme.quizCardColorA
import com.example.rehealth.ui.theme.quizCardColorB
import com.example.rehealth.ui.theme.quizIconColorA
import com.example.rehealth.ui.theme.quizIconColorB
import com.example.rehealth.ui.theme.yellow10

@Composable
fun QuizHomeScreen(navHostController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(menuItemColor4)
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

        QuizMenu(cardColor = quizCardColorA, iconColor = quizIconColorA, "سوالات سری اول"){

            //on click listener
            navHostController.navigate("QuestionsScreenRoute/1")

        }

        QuizMenu(cardColor = quizCardColorB, iconColor = quizIconColorB, "سوالات سری دوم"){

            //on click listener
            navHostController.navigate("QuestionsScreenRoute/2")
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
                        ).clickable { onArrowClick() }, contentAlignment = Alignment.Center
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
                    modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
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