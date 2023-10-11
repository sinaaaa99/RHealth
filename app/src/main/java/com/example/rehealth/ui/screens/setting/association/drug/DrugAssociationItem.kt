package com.example.rehealth.ui.screens.setting.association.drug

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.rehealth.R
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.ui.theme.associationIconBackground
import com.example.rehealth.ui.theme.drugItemNumColor
import java.time.LocalDateTime
import java.util.UUID

@Composable
fun DrugAssociationItem(drugReminder: DrugReminder, itemNumber: Int, ringCount: Long) {

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
                    modifier = Modifier.fillMaxWidth(),
                    text = drugReminder.name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    //association.............................
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(associationIconBackground)
                                .padding(4.dp), contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_bell_hand),
                                contentDescription = "icon bell hand",
                                tint = Color.White
                            )

                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = drugReminder.userAssociation.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = associationIconBackground
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "مشارکت",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .height(50.dp)
                            .width(1.dp), color = Color.Black
                    )

                    //bell......................................
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(associationIconBackground)
                                .padding(6.dp), contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(id = R.drawable.ic_bell),
                                contentDescription = "icon bell ",
                                tint = Color.White
                            )

                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = ringCount.toString(),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = associationIconBackground
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "هشدارها",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }


            }

        }


    }
}

@Composable
@Preview(showBackground = true)
fun DrugAssociationItemPreview() {
    DrugAssociationItem(
        drugReminder = DrugReminder(
            UUID.randomUUID(),
            1,
            "شیفت صبح",
            LocalDateTime.now(),
            1,
            2
        ), 1, 2
    )
}