package com.example.rehealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rehealth.data.convertor.TimeConvertor
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects

@Database(entities = [Medicines::class, SideEffects::class,DrugReminder::class], version = 1, exportSchema = false)
@TypeConverters(TimeConvertor::class)
abstract class ReHealthDB : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao


}