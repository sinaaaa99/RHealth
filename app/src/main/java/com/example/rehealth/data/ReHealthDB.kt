package com.example.rehealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rehealth.data.convertor.TimeConvertor
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.data.models.DrugsClass
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.models.quiz.QuizClass
import com.example.rehealth.data.models.quiz.UserAnswer

@Database(
    entities = [
        Medicines::class,
        SideEffects::class,
        DrugReminder::class,
        DrugsClass::class,
        TestReminder::class,
        VisitReminder::class,
        QuizClass::class,
        UserAnswer::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TimeConvertor::class)
abstract class ReHealthDB : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao


}