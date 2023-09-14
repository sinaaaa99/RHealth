package com.example.rehealth.data.models.quiz

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("quizId")])
data class QuizClass(
    @PrimaryKey
    val quizId:Int,
    val type:Int,
    val title:String,
    val answerText1:String,
    val answerText2:String
)
