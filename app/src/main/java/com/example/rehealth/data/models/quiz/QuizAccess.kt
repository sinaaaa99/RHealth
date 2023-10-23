package com.example.rehealth.data.models.quiz

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class QuizAccess(
    @PrimaryKey
    val id: Int,
    val lastTake: LocalDateTime
)