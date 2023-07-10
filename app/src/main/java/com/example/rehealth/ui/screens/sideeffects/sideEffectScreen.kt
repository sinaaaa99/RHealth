package com.example.rehealth.ui.screens.sideeffects


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.ui.theme.green30
import com.example.rehealth.ui.viewmodel.SharedViewModel
import com.example.rehealth.util.RequestState

@Composable
fun SideEffectScreen(
    medicineType: Int,
    medicineWithSideEffects: RequestState<List<MedicineWithSideEffects>>
) {

//    val medicineWithSideEffects by sharedViewModel.medicineWithSideEffects.collectAsState()


    val scrollable = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollable)
    ) {

        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Text(
                modifier = Modifier.padding(4.dp),
                text = "هر دراویی ممکن است عوارضی داشته باشد که لزوما در همه ی بیماران ایجاد نمیگردد",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        if (medicineWithSideEffects is RequestState.Success) {

            medicineWithSideEffects.data[medicineType].sideEffects.forEach { sideEffect ->

                var descriptionVisible by remember {
                    mutableStateOf(false)
                }


                val angle1 by animateFloatAsState(
                    targetValue = if (descriptionVisible) 180f else 0f, animationSpec = tween(
                        400
                    )
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 4.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                descriptionVisible = !descriptionVisible
                            },
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow),
                            contentDescription = "side effect icon drop down",
                            modifier = Modifier.rotate(
                                angle1
                            ), tint = if (descriptionVisible) green30 else Color.Black
                        )

                        Text(
                            text = sideEffect.title,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    AnimatedVisibility(visible = descriptionVisible) {


                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = sideEffect.description,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Right,
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }

                    }
                }

            }
        }


    }

}