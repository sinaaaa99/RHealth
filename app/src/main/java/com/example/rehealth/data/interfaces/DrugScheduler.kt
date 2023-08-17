package com.example.rehealth.data.interfaces

import com.example.rehealth.data.models.DrugReminder

interface DrugScheduler {

    fun schedule(drugReminder: DrugReminder)
    fun cancel(drugReminder: DrugReminder)
    fun update(drugReminder: DrugReminder)
}