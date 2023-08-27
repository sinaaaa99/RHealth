package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity("TestReminder")
data class TestReminder(
    @PrimaryKey
    val testId:UUID,
    val alarmId:Int,
    val name:String,
    val time:LocalDateTime

)
