package com.example.rehealth.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LockClass(
    @PrimaryKey
    val id: Int,
    val password: String,
    val isLock: Boolean
)
