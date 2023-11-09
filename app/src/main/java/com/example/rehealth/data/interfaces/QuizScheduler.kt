package com.example.rehealth.data.interfaces

import com.example.rehealth.data.models.QuizReminder

interface QuizScheduler {

    fun schedule(quizReminder: QuizReminder)
    fun cancel(quizReminder: QuizReminder)
    fun repeat(quizReminder: QuizReminder)
}