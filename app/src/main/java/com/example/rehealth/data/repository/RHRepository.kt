package com.example.rehealth.data.repository

import com.example.rehealth.data.MedicineDao
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RHRepository @Inject constructor(private val medicineDao: MedicineDao) {

    val getAllData: Flow<List<Medicines>> = medicineDao.getAllData()

    val getMedicineWithSideEffects: Flow<List<MedicineWithSideEffects>> =
        medicineDao.getMedicineWithSide()

    //insert data in medicines table
    suspend fun insertMedicines(medicines: Medicines) = medicineDao.insertMedicines(medicines)

    suspend fun insertSideEffects(sideEffects: SideEffects) =
        medicineDao.insertSideEffects(sideEffects)
}