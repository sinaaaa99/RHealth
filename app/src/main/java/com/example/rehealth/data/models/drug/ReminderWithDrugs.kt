package com.example.rehealth.data.models.drug

import androidx.room.Embedded
import androidx.room.Relation

data class ReminderWithDrugs(
    @Embedded val reminder: DrugReminder,
    @Relation(parentColumn = "reminderId", entityColumn = "reminderId")
    val drugs: List<DrugsClass>

)
