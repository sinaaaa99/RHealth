package com.example.rehealth.data.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicines(
    @PrimaryKey
    @ColumnInfo(name = "medicineId")
    val id: Int,
    val drugName: String,
    val advice: String,
    val medicineType:Int,
    val otherName:String,
)