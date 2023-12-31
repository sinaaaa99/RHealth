package com.example.rehealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rehealth.data.convertor.TimeConvertor
import com.example.rehealth.data.models.LockClass
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.data.models.drug.DrugsClass
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.QuizReminder
import com.example.rehealth.data.models.SideEffects
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.UserIdentification
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.models.quiz.QuizAccess
import com.example.rehealth.data.models.quiz.QuizClass
import com.example.rehealth.data.models.quiz.QuizResult
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
        UserAnswer::class,
        QuizResult::class,
        QuizReminder::class,
        UserIdentification::class,
        LockClass::class,
        QuizAccess::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(TimeConvertor::class)
abstract class ReHealthDB : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao


}