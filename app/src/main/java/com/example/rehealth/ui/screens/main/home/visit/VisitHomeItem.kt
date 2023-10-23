package com.example.rehealth.ui.screens.main.home.visit

import androidx.compose.foundation.background
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
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.ui.theme.drugBoxColor
import com.example.rehealth.ui.theme.drugItemNumColor
import com.example.rehealth.ui.theme.greenCheck
import com.example.rehealth.ui.theme.red50
import java.time.format.DateTimeFormatter

@Composable
fun VisitHomeItem(visitReminder: VisitReminder, itemNumber: Int) {

    val userAssociation = visitReminder.userAssociation
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
                    text = "نوبت ویزیت",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = visitReminder.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
                                text = visitReminder.visitInfo,
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
                                text = visitReminder.time.format(DateTimeFormatter.ofPattern("hh:mm")),
                                style = MaterialTheme.typography.titleSmall
                            )

                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(color = drugBoxColor, shape = CircleShape)
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = if (userAssociation) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = "association cheek icon of tests",
                            tint = if (userAssociation) greenCheck else red50
                        )
                    }
                }
            }

        }


    }
}