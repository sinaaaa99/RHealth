package com.example.rehealth.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class MedicineWithSideEffects(

    @Embedded val medicines: Medicines,
    @Relation(
        parentColumn = "medicineId",
        entityColumn = "medicineId"
    ) val sideEffects: List<SideEffects>
)
