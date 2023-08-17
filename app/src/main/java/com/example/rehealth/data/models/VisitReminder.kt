package com.example.rehealth.data.models

import java.time.LocalDateTime

data class VisitReminder(
    val id:Int,
    val time: LocalDateTime,
    val title:String,
    val description:String
)