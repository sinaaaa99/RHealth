package com.example.rehealth.data.interfaces

import com.example.rehealth.data.models.drug.DrugReminder

interface DrugScheduler {

    fun schedule(drugReminder: DrugReminder)
    fun cancel(drugReminder: DrugReminder)
    fun repeat(drugReminder: DrugReminder)
}