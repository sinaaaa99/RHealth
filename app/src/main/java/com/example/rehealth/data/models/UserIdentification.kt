package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserIdentification(
    @PrimaryKey
    val id: Int,
    val name: String,
    val age: String,
    val problem: String
)
