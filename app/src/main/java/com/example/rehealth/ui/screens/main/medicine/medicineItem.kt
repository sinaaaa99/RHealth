package com.example.rehealth.ui.screens.main.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.ui.theme.green20

@Composable
fun MedicineItem(
    title: String,
    subtitle: String,
    color: Color,
    itemIndex: Int,
    onClick: (Int) -> Unit
) {


    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick(itemIndex)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp, pressedElevation = 12.dp),
        colors = CardDefaults.cardColors(color)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(9f)
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Right,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = subtitle,
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Right,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(32.dp))
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_tablet),
                    contentDescription = "pic doctor"
                )
            }


        }


    }
}

@Preview
@Composable
fun MedicineItemPreview() {

//    MedicineItem()
}