package com.example.rehealth.data.models

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class DrugsClass (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val shiftId:UUID,
    val name:String,
    val shiftTime:Int,
    val drugsNumber:Int
)