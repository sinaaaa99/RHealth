package com.example.rehealth.data.interfaces

import com.example.rehealth.data.models.TestReminder

interface TestScheduler {

    fun schedule(testReminder: TestReminder)
    fun cancel(testReminder: TestReminder)
}