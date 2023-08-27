package com.example.rehealth.data.interfaces

import com.example.rehealth.data.models.VisitReminder

interface VisitScheduler {

    fun schedule(visitReminder: VisitReminder)
    fun cancel(visitReminder: VisitReminder)
}