package com.example.rehealth.data.models.drug

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    foreignKeys = [ForeignKey(
        entity = DrugReminder::class,
        parentColumns = ["reminderId"],
        childColumns = ["reminderId"],
        onDelete = CASCADE
    )],
    indices = [Index("reminderId")]
)
data class DrugsClass(
    @PrimaryKey(autoGenerate = true)
    val drugId: Int,
    val reminderId: UUID,
    val drugName: String,
    val drugsNumber: String,
    val shiftCode:Int,
    val advice:String
)