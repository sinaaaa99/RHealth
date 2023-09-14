package com.example.rehealth.data.models.quiz

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    foreignKeys = [ForeignKey(
        entity = QuizClass::class,
        ["quizId"],
        ["quizId"],
        onDelete = CASCADE
    )],
    indices = [Index("quizId")]
)
data class UserAnswer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val quizId: Int,
    val answerRate: Int,
    val dateTime: LocalDateTime
)
