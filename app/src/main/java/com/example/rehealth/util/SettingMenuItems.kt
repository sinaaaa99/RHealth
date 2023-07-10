package com.example.rehealth.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.rehealth.R
import com.example.rehealth.ui.theme.drugSettingBack
import com.example.rehealth.ui.theme.quizSettingBack
import com.example.rehealth.ui.theme.testSettingBack
import com.example.rehealth.ui.theme.visitSettingBack

enum class SettingMenuItems(
    val id: Int,
    val route: String,
    val title: String,
    val image: Int,
    val backColor: Color,
    val height: Dp
) {
    Visits(1, "Visit_item", "ویزیت", R.drawable.pic_visit, visitSettingBack,200.dp),
    Drugs(2, "Drugs_item", "دارو", R.drawable.pic_drug_set, drugSettingBack,250.dp),
    Tests(3, "Test_item", "آزمایش", R.drawable.pic_test_set, testSettingBack,250.dp),
    Questions(4, "Question_item", "پرسشنامه", R.drawable.pic_quiz, quizSettingBack,200.dp)


}