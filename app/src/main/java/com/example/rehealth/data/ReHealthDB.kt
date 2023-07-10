package com.example.rehealth.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects

@Database(entities = [Medicines::class, SideEffects::class], version = 1, exportSchema = false)
abstract class ReHealthDB : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao


}