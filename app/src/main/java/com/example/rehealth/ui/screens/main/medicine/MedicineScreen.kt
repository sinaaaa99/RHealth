package com.example.rehealth.ui.screens.main.medicine

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rehealth.MedicineActivity
import com.example.rehealth.R
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.ui.theme.blue30
import com.example.rehealth.ui.theme.cream10
import com.example.rehealth.ui.theme.green20
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.theme.orange20
import com.example.rehealth.ui.theme.red50
import com.example.rehealth.ui.theme.yellow70
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@Composable
fun MedicineScreen(sharedViewModel: SharedViewModel) {

    val allData by sharedViewModel.allData.collectAsState()

    val context = LocalContext.current



    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "medicine search icon"
                )

                Text(text = "Knowledge \n is power", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            }

            Image(
                painter = painterResource(id = R.drawable.pic_books),
                contentDescription = "medication books image"
            )
        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .weight(8f), shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(6.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                    Box(modifier = Modifier.size(36.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.pic_medicine),
                            contentDescription = "medicine pic"
                        )
                    }


                    Text(
                        text = "داروها",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Right
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn {

                    if (allData is RequestState.Success) {

                        itemsIndexed((allData as RequestState.Success<List<Medicines>>).data) { index, medicine ->

                            MedicineItem(
                                title = medicine.drugName,
                                subtitle = medicine.otherName,
                                color = when (medicine.medicineType) {

                                    1 -> green20
                                    2 -> cream10
                                    3 -> yellow70
                                    4 -> blue30
                                    5 -> red50
                                    6 -> green30
                                    7 -> orange20
                                    else -> green20
                                }, index
                            ) {

                                val intent = Intent(context, MedicineActivity::class.java)
                                intent.putExtra("medicineType", index)
                                context.startActivity(intent)

                            }
                        }
                    }


                }

            }

        }
    }
}

@Preview
@Composable
fun MedicineScreenPreview() {

//    MedicineScreen()

}