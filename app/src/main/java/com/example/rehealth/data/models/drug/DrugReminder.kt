package com.example.rehealth.data.models.drug

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity(indices = [Index("reminderId")])
data class DrugReminder(
    @PrimaryKey
    val reminderId: UUID,
    val alarmId: Int,
    val name: String,
    val reminder: LocalDateTime,
    val shiftCode: Int,
    val userAssociation: Int
)
