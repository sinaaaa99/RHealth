package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.UUID

@Entity
data class VisitReminder(
    @PrimaryKey
    val visitId:UUID,
    val alarmId:Int,
    val title:String,
    val time: LocalDateTime,
    val userAssociation:Boolean
)