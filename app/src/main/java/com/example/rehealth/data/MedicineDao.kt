package com.example.rehealth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: Medicines)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSideEffects(sideEffects: SideEffects)

    @Query("select * from medicines order by medicineId ASC")
    fun getAllData(): Flow<List<Medicines>>

    @Transaction
    @Query("select * from medicines order by medicineId ASC")
    fun getMedicineWithSide(): Flow<List<MedicineWithSideEffects>>
}