package com.example.rehealth.ui.screens.setting.association

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rehealth.data.models.association.AssociationMenuItem
import com.example.rehealth.ui.theme.drugBackgroundColor
import com.example.rehealth.ui.theme.green32

@Composable
fun AssociationItems(associationMenuItem: AssociationMenuItem, onCardClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onCardClick()
            },
        colors = CardDefaults.cardColors(containerColor = green32)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier, contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(35.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Recycler bin icon"
                )
            }


            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = associationMenuItem.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(color = drugBackgroundColor)
                    .padding(12.dp)
            ) {

                Image(
                    painter = painterResource(id = associationMenuItem.image),
                    contentDescription = "pills item image"
                )

            }


        }
    }
}

@Composable
@Preview(showBackground = true)
fun AssociationItemsPreview() {

//    AssociationItems()
}