package com.example.rehealth.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SideEffects(
    @PrimaryKey
    val id:Int,
    val title:String,
    val description:String,
    @ColumnInfo(name = "medicineId")
    val medicineId:Int
)
