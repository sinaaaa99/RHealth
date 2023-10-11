package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class QuizReminder(
    @PrimaryKey(autoGenerate = true)
    val quizId: Int,
    val alarmId: Int,
    val name: String,
    val reminder: LocalDateTime
)
