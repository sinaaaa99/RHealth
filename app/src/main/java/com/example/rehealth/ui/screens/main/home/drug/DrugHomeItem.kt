package com.example.rehealth.ui.screens.main.home.drug

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.data.models.drug.DrugsClass
import com.example.rehealth.ui.theme.drugBoxColor
import com.example.rehealth.ui.theme.drugItemNumColor
import java.time.format.DateTimeFormatter

@Composable
fun DrugHomeItem(
    drugsClass: DrugsClass,
    reminder: DrugReminder,
    itemNumber: Int,
    onAdviceClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Column(
            modifier = Modifier
                .height(120.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            Box(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .weight(8f), contentAlignment = Alignment.Center
            ) {


                if (itemNumber > 1) {

                    Divider(
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                    )
                }


            }

            Box(
                modifier = Modifier
                    .size(30.dp)
                    .weight(2f)
                    .background(color = drugItemNumColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = itemNumber.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )

            }
        }

        Card(
            modifier = Modifier.weight(15f),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = reminder.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = drugsClass.drugName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .background(color = drugBoxColor, shape = CircleShape)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = " ${drugsClass.drugsNumber} عدد ",
                                style = MaterialTheme.typography.titleSmall
                            )

                        }

                        Box(
                            modifier = Modifier
                                .background(color = drugBoxColor, shape = CircleShape)
                                .padding(6.dp),
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                modifier = Modifier.padding(2.dp),
                                text = reminder.reminder.format(DateTimeFormatter.ofPattern("hh:mm")),
                                style = MaterialTheme.typography.titleSmall
                            )

                        }
                    }

                    Row(
                        modifier = Modifier
                            .background(color = drugBoxColor, shape = CircleShape)
                            .clickable {

                                onAdviceClick()
                            }
                            .padding(vertical = 6.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = "توصیه",
                            style = MaterialTheme.typography.titleSmall
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.ic_advice),
                            contentDescription = "advice icon"
                        )
                    }

                }
            }

        }


    }
}

@Composable
@Preview(showBackground = true)
fun DrugHomeItemShow() {

//    DrugHomeItem(1, 1)
}