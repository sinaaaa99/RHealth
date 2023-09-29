package com.example.rehealth.data.models.quiz

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizResult(
    @PrimaryKey
    val id: Int = 0,
    val userCheek1: Int = 0,
    val userCheek2: Int = 0
)