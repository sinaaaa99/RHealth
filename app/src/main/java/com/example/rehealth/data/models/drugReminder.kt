package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "Drug_Reminder")
data class DrugReminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val dooz: String,
    val reminder1:LocalDateTime
)
