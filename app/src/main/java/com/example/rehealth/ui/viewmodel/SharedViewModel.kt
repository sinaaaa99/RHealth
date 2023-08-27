package com.example.rehealth.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.data.models.DrugsClass
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.prepopulate.PrepopulateMedicine
import com.example.rehealth.data.repository.RHRepository
import com.example.rehealth.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
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

    //Insert drug Reminder..............................

    val drugId: MutableState<UUID> by lazy {
        mutableStateOf(UUID.randomUUID())
    }
    val alarmId: MutableState<Int> by lazy {
        mutableStateOf((1..2000000000).random())
    }
    val timeShiftName: MutableState<String> = mutableStateOf("")


    val timeReminder: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())

    //insert Drug Reminder
    fun insertDrugReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val drugReminder = DrugReminder(
                drugId.value,
                alarmId.value,
                timeShiftName.value,
                timeReminder.value,
                shiftCode.value
            )

            repository.insertDrugReminder(drugReminder)

            timeShiftName.value = ""
            timeReminder.value = LocalDateTime.now()

            drugId.value = UUID.randomUUID()
            alarmId.value = (1..2000000000).random()
            shiftCode.value = 0
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

    //delete Drug Reminder
    fun deleteDrugReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteDrugReminder(drugId.value)
        }
    }

    //Drugs functions..............................

    val shiftCode: MutableState<Int> = mutableStateOf(0)
    val drugsNumber: MutableState<Int> = mutableStateOf(1)
    val drugName: MutableState<String> = mutableStateOf("")
    fun insertDrugs() {
        viewModelScope.launch(Dispatchers.IO) {

            val drugClass = DrugsClass(
                0,
                drugId.value,
                drugName.value,
                shiftCode.value,
                drugsNumber.value
            )

            repository.insertDrugs(drugClass)

            drugName.value = ""
            drugsNumber.value = 1

        }

    }

    //read drugs based on time shift
    private val _drugs = MutableStateFlow<RequestState<List<DrugsClass>>>(RequestState.Idle)
    val drugs: StateFlow<RequestState<List<DrugsClass>>> = _drugs

    fun getDrugs() {

        _drugs.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getDrugs(shiftCode.value).collect { listOfDrugs ->

                _drugs.value = RequestState.Success(listOfDrugs)

            }
        }

    }

    //delete one drug
    val eachDrugId: MutableState<Int> = mutableStateOf(0)
    fun deleteDrug() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteDrug(eachDrugId.value)
        }
    }

    fun deleteShiftDrug() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteShiftDrug(drugId.value)
        }
    }

    //Tests Function............................
    val testId: MutableState<UUID> by lazy {

        mutableStateOf(UUID.randomUUID())
    }

    val alarmIdTest: MutableState<Int> by lazy {
        mutableStateOf((1..2000000000).random())
    }

    val testName: MutableState<String> = mutableStateOf("")

    val testTimeReminder: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())

    //insert Tests
    fun insertTestsReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val testReminder = TestReminder(
                testId.value,
                alarmIdTest.value,
                testName.value,
                testTimeReminder.value
            )

            repository.insertTestsReminder(testReminder)

            testId.value = UUID.randomUUID()
            alarmIdTest.value = (1..2000000000).random()
            testName.value = ""
            testTimeReminder.value = LocalDateTime.now()

        }
    }

    //read Tests
    private val _allTest = MutableStateFlow<RequestState<List<TestReminder>>>(RequestState.Idle)
    val allTest: StateFlow<RequestState<List<TestReminder>>> = _allTest


    private fun getAllTests() {

        _allTest.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllTests.collect { listOfTestReminder ->

                _allTest.value = RequestState.Success(listOfTestReminder)

            }
        }
    }

    //delete test
    fun deleteTest() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteTest(testId.value)
        }
    }




    //Visit Function............................
    val visitId: MutableState<UUID> by lazy {

        mutableStateOf(UUID.randomUUID())
    }

    val alarmIdVisit: MutableState<Int> by lazy {
        mutableStateOf((1..2000000000).random())
    }

    val doctorName: MutableState<String> = mutableStateOf("")

    val visitTimeReminder: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())

    //insert Tests
    fun insertVisitReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val visitReminder = VisitReminder(
                visitId.value,
                alarmIdVisit.value,
                doctorName.value,
                visitTimeReminder.value
            )

            repository.insertVisitsReminder(visitReminder)

            visitId.value = UUID.randomUUID()
            alarmIdVisit.value = (1..2000000000).random()
            doctorName.value = ""
            visitTimeReminder.value = LocalDateTime.now()

        }
    }

    //read Tests
    private val _allVisit = MutableStateFlow<RequestState<List<VisitReminder>>>(RequestState.Idle)
    val allVisit: StateFlow<RequestState<List<VisitReminder>>> = _allVisit


    private fun getAllVisit() {

        _allVisit.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllVisits.collect { listOfVisitReminder ->

                _allVisit.value = RequestState.Success(listOfVisitReminder)

            }
        }
    }

    //delete test
    fun deleteVisit() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteVisit(visitId.value)
        }
    }


    init {

        //medicine information
        prePopulateDataInDB()
        getAllData()
        getMedicineWithSideEffects()

        //read Drugs
        readAllDrugsReminder()

        //read tests
        getAllTests()

        //read visits
        getAllVisit()

//        getDrugs()
    }


}