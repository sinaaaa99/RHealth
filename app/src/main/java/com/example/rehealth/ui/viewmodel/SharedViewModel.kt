package com.example.rehealth.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.prepopulate.PrepopulateMedicine
import com.example.rehealth.data.repository.RHRepository
import com.example.rehealth.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository: RHRepository) : ViewModel() {


    //adding data in database
    private fun prePopulateDataInDB() {

        viewModelScope.launch(Dispatchers.IO) {

            for (question in PrepopulateMedicine.addMedicines) {

                repository.insertMedicines(question)
            }

            for (sideEffect in PrepopulateMedicine.addSideEffects) {

                repository.insertSideEffects(sideEffect)

            }
        }
    }


    //reading All medicine  data
    private val _allData = MutableStateFlow<RequestState<List<Medicines>>>(RequestState.Idle)
    val allData: StateFlow<RequestState<List<Medicines>>> = _allData

    private fun getAllData() {
        _allData.value = RequestState.Loading

        try {

            viewModelScope.launch {

                repository.getAllData.collect { medicinesList ->

                    _allData.value = RequestState.Success(medicinesList)

                }
            }

        } catch (e: Exception) {

            _allData.value = RequestState.Error(e)

        }


    }

    ////reading medicine with sideEffects
    private val _medicineWithSideEffects =
        MutableStateFlow<RequestState<List<MedicineWithSideEffects>>>(RequestState.Idle)
    val medicineWithSideEffects: StateFlow<RequestState<List<MedicineWithSideEffects>>> =
        _medicineWithSideEffects

    private fun getMedicineWithSideEffects() {

        _medicineWithSideEffects.value = RequestState.Loading

        try {
            viewModelScope.launch {
                repository.getMedicineWithSideEffects.collect { medicineWithSideEffectsList ->

                    _medicineWithSideEffects.value =
                        RequestState.Success(medicineWithSideEffectsList)

                }

            }

        } catch (e: Exception) {

            _medicineWithSideEffects.value = RequestState.Error(e)

        }
    }

    //Insert drug Reminder

    val drugId: MutableState<Int> = mutableStateOf(0)
    val drugTitle: MutableState<String> = mutableStateOf("")
    val drugDooz: MutableState<String> = mutableStateOf("")
    val timeReminder1: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())
    private fun insertDrugReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val drugReminder = DrugReminder(
                drugId.value,
                drugTitle.value,
                drugDooz.value,
                timeReminder1.value
            )

            repository.insertDrugReminder(drugReminder)
        }
    }

    //reading Drug Reminder
    private val _allDrugs = MutableStateFlow<RequestState<List<DrugReminder>>>(RequestState.Idle)
    val allDrugs: StateFlow<RequestState<List<DrugReminder>>> = _allDrugs

    private fun readAllDrugsReminder() {

        _allDrugs.value = RequestState.Loading

        try {
            viewModelScope.launch {

                repository.getAllDrugReminder.collect { drugsReminder ->

                    _allDrugs.value = RequestState.Success(drugsReminder)


                }
            }
        } catch (e: Exception) {

            _allDrugs.value = RequestState.Error(e)

        }
    }

    init {

        prePopulateDataInDB()
        getAllData()
        getMedicineWithSideEffects()

        readAllDrugsReminder()
    }


}