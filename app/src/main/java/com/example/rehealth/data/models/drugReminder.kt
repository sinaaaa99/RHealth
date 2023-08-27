package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(tableName = "Drug_Reminder")
data class DrugReminder(
    @PrimaryKey
    val drugId: UUID,
    val alarmId:Int,
    val name: String,
    val reminder: LocalDateTime,
    val shiftCode:Int
)
