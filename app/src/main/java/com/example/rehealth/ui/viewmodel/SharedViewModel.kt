package com.example.rehealth.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.data.models.drug.DrugsClass
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.QuizReminder
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.UserIdentification
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.models.drug.ReminderWithDrugs
import com.example.rehealth.data.models.quiz.QuizClass
import com.example.rehealth.data.models.quiz.QuizResult
import com.example.rehealth.data.models.quiz.UserAnswer
import com.example.rehealth.data.prepopulate.InitQuiz
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

    val reminderId: MutableState<UUID> by lazy {
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
                reminderId.value,
                alarmId.value,
                timeShiftName.value,
                timeReminder.value,
                shiftCode.value,
                0
            )

            repository.insertDrugReminder(drugReminder)

            timeShiftName.value = ""
            timeReminder.value = LocalDateTime.now()

            reminderId.value = UUID.randomUUID()
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

            repository.deleteDrugReminder(reminderId.value)
        }
    }

    //Drugs functions..............................

    val shiftCode: MutableState<Int> = mutableStateOf(0)
    val drugsNumber: MutableState<String> = mutableStateOf("")
    val drugName: MutableState<String> = mutableStateOf("")
    val drugAdvice: MutableState<String> = mutableStateOf("")
    fun insertDrugs() {

        viewModelScope.launch(Dispatchers.IO) {

            val drugClass = DrugsClass(
                0,
                reminderId.value,
                drugName.value,
                drugsNumber.value,
                shiftCode.value,
                drugAdvice.value
            )

            repository.insertDrugs(drugClass)

            drugName.value = ""
            drugsNumber.value = ""
            drugAdvice.value = ""

        }

    }

    //read drugs based on reminder id
    private val _reminderWithDrugs =
        MutableStateFlow<RequestState<ReminderWithDrugs>>(RequestState.Idle)
    val reminderWithDrugs: StateFlow<RequestState<ReminderWithDrugs>> = _reminderWithDrugs

    fun getReminderWithDrugs() {

        _reminderWithDrugs.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getReminderWithDrugs(reminderId.value).collect { listOfReminderWithDrugs ->

                _reminderWithDrugs.value = RequestState.Success(listOfReminderWithDrugs)

            }
        }

    }


    //read drugs based on  shift
    private val _reminderWithDrugsByShift =
        MutableStateFlow<RequestState<ReminderWithDrugs?>>(RequestState.Idle)
    val reminderWithDrugsByShift: StateFlow<RequestState<ReminderWithDrugs?>> =
        _reminderWithDrugsByShift

    fun getReminderWithDrugsByShift() {

        _reminderWithDrugsByShift.value = RequestState.Loading

        try {

            viewModelScope.launch(Dispatchers.IO) {

                repository.getReminderWithDrugsByShift(shiftCode.value)
                    .collect { listOfReminderWithDrugsByShift ->

                        _reminderWithDrugsByShift.value =
                            RequestState.Success(listOfReminderWithDrugsByShift)

                    }
            }

        } catch (e: Exception) {

            _reminderWithDrugsByShift.value = RequestState.Error(e)

        }

    }

    //get All drugs By Id
    /*private val _drugsById = MutableStateFlow<RequestState<List<DrugsClass>>>(RequestState.Idle)
    val drugsById: StateFlow<RequestState<List<DrugsClass>>> = _drugsById


    fun getDrugsById() {

        _drugsById.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllDrugsById(drugId.value).collect { listOfDrugs ->

                _drugsById.value = RequestState.Success(listOfDrugs)

            }
        }

    }*/

    //delete one drug
    val eachDrugId: MutableState<Int> = mutableStateOf(0)
    fun deleteDrug() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteDrug(eachDrugId.value)
        }
    }

    /*fun deleteShiftDrug() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteShiftDrug(drugId.value)
        }
    }*/

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
                testTimeReminder.value,
                false
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

    //insert Visits
    fun insertVisitReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val visitReminder = VisitReminder(
                visitId.value,
                alarmIdVisit.value,
                doctorName.value,
                visitTimeReminder.value,
                false
            )

            repository.insertVisitsReminder(visitReminder)

            visitId.value = UUID.randomUUID()
            alarmIdVisit.value = (1..2000000000).random()
            doctorName.value = ""
            visitTimeReminder.value = LocalDateTime.now()

        }
    }

    //read Visit
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

    //delete Visit
    fun deleteVisit() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteVisit(visitId.value)
        }
    }


    //Quiz Functions......................................

    //insert
    private fun insertQuiz() {

        viewModelScope.launch {


            for (quiz in InitQuiz.listOfQuiz) {

                repository.insertQuiz(quiz)
            }

        }
    }

    private val _allQuiz = MutableStateFlow<RequestState<List<QuizClass>>>(RequestState.Idle)
    val allQuiz: StateFlow<RequestState<List<QuizClass>>> = _allQuiz


    val quizType: MutableState<Int> = mutableStateOf(1)
    var currentQuestion1: MutableState<Int> = mutableStateOf(0)
    var currentQuestion2: MutableState<Int> = mutableStateOf(0)

    fun getQuiz() {
        _allQuiz.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getQuiz(quizType.value).collect { quizList ->

                _allQuiz.value = RequestState.Success(quizList)
            }
        }

    }

    //insert User Answer
    var quizId: MutableState<Int> = mutableStateOf(0)
    var userAnswerRate: MutableState<Int> = mutableStateOf(0)
    var userAnswerDate: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())
    private fun insertUserAnswer() {


        viewModelScope.launch(Dispatchers.IO) {

            val userAnswer = UserAnswer(
                0,
                quizId.value,
                userAnswerRate.value,
                userAnswerDate.value
            )

            repository.insertUserAnswer(userAnswer)

        }

    }

    fun changeQuestion() {


        viewModelScope.launch(Dispatchers.IO) {


            insertUserAnswer()

            if (quizType.value == 1 && currentQuestion1.value < 6) {

                currentQuestion1.value++
            }
            if (quizType.value == 2 && currentQuestion2.value < 6) {

                currentQuestion2.value++
            }


        }

    }


    //Quiz Result for doctor advice

    private val _userCheeks = MutableStateFlow<RequestState<QuizResult>>(RequestState.Idle)
    val userCheeks: StateFlow<RequestState<QuizResult>> = _userCheeks

    var userCheeks1: MutableState<Int> = mutableStateOf(0)
    var userCheeks2: MutableState<Int> = mutableStateOf(0)


    private fun insertFirstUserCheeks() {

        val quizResult = QuizResult(0, 0, 0)

        viewModelScope.launch(Dispatchers.IO) {

            repository.insertFirstUserCheeks(quizResult)
        }
    }

    fun getUserCheeks() {

        _userCheeks.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.readUserCheeks.collect { quizCheeks ->

                _userCheeks.value = RequestState.Success(quizCheeks)

            }
        }
    }


    fun updateQuizResult1() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateQuizResult1(userCheeks1.value)
        }

    }

    fun updateQuizResult2() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateQuizResult2(userCheeks2.value)
        }

    }

    //user Association................................

    //test
    val testAlarmId: MutableState<Int> = mutableStateOf(0)
    fun updateTestAssociation() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateTestAssociation(testAlarmId.value)
        }
    }

    private val _testSize = MutableStateFlow<RequestState<Int>>(RequestState.Idle)
    val testSize: StateFlow<RequestState<Int>> = _testSize

    private val _testAssociation = MutableStateFlow<RequestState<Int>>(RequestState.Idle)
    val testAssociation: StateFlow<RequestState<Int>> = _testAssociation

    fun getTestAssociation() {

        _testAssociation.value = RequestState.Loading

        try {
            viewModelScope.launch(Dispatchers.IO) {

                repository.getTestAssociation.collect {

                    _testAssociation.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _testAssociation.value = RequestState.Error(e)
        }

    }

    fun getTestSize() {

        _testSize.value = RequestState.Loading

        try {

            viewModelScope.launch(Dispatchers.IO) {

                repository.getTestSize.collect {

                    _testSize.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _testSize.value = RequestState.Error(e)
        }

    }

    //visit
    val visitAlarmId: MutableState<Int> = mutableStateOf(0)
    fun updateVisitAssociation() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateVisitAssociation(visitAlarmId.value)
        }
    }

    private val _visitSize = MutableStateFlow<RequestState<Int>>(RequestState.Idle)
    val visitSize: StateFlow<RequestState<Int>> = _visitSize

    private val _visitAssociation = MutableStateFlow<RequestState<Int>>(RequestState.Idle)
    val visitAssociation: StateFlow<RequestState<Int>> = _visitAssociation

    fun getVisitAssociation() {

        _visitAssociation.value = RequestState.Loading

        try {

            viewModelScope.launch(Dispatchers.IO) {

                _visitAssociation.value = RequestState.Loading

                repository.getVisitAssociation.collect {

                    _visitAssociation.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _visitAssociation.value = RequestState.Error(e)
        }

    }

    fun getVisitSize() {

        _visitSize.value = RequestState.Loading

        try {
            viewModelScope.launch(Dispatchers.IO) {

                repository.getVisitSize.collect {

                    _visitSize.value = RequestState.Success(it)
                }
            }
        } catch (e: Exception) {
            _visitSize.value = RequestState.Error(e)
        }

    }

    //Drug
    val drugAlarmId: MutableState<Int> = mutableStateOf(0)
    fun updateDrugAssociation() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.updateDrugAssociation(drugAlarmId.value)
        }
    }


    //Quiz Reminder fun..............................
    val quizReminderId: MutableState<Int> by lazy {
        mutableStateOf(0)
    }
    val quizAlarmId: MutableState<Int> by lazy {
        mutableStateOf((1..2000000000).random())
    }

    val quizAlarmName: MutableState<String> = mutableStateOf("")

    val quizTimeReminder: MutableState<LocalDateTime> = mutableStateOf(LocalDateTime.now())

    //insert Quiz Reminder
    fun insertQuizReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            val quizReminder = QuizReminder(
                0,
                quizAlarmId.value,
                quizAlarmName.value,
                quizTimeReminder.value
            )

            repository.insertQuizReminder(quizReminder)

//            visitId.value = UUID.randomUUID()
            quizAlarmId.value = (1..2000000000).random()
            quizAlarmName.value = ""
            quizTimeReminder.value = LocalDateTime.now()

        }
    }

    //read Quiz Reminder
    private val _allQuizReminder =
        MutableStateFlow<RequestState<List<QuizReminder>>>(RequestState.Idle)
    val allQuizReminder: StateFlow<RequestState<List<QuizReminder>>> = _allQuizReminder


    fun getAllQuizReminder() {

        _allQuizReminder.value = RequestState.Loading

        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllQuizReminder.collect { listOfQuizReminder ->

                _allQuizReminder.value = RequestState.Success(listOfQuizReminder)
            }
        }
    }

    //delete Quiz Reminder
    fun deleteQuizReminder() {

        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteQuizReminder(quizReminderId.value)
        }
    }

    //user Identify

    //insert user Identify
    val userName: MutableState<String> = mutableStateOf("")
    val userProblem: MutableState<String> = mutableStateOf("")
    val userAge: MutableState<String> = mutableStateOf("")


    fun insertUserIdentify() {

        viewModelScope.launch(Dispatchers.IO) {

            val userIdentify = UserIdentification(
                0,
                userName.value,
                userAge.value,
                userProblem.value
            )
            repository.insertUserIdentification(userIdentify)

        }
    }

    //reading user Identify
    private val _userIdentify =
        MutableStateFlow<RequestState<UserIdentification?>>(RequestState.Idle)
    val userIdentify: StateFlow<RequestState<UserIdentification?>> = _userIdentify

    fun readUserIdentify() {

        _userIdentify.value = RequestState.Loading

        try {
            viewModelScope.launch {

                repository.readUserIdentification.collect { Identify ->

                    _userIdentify.value = RequestState.Success(Identify)


                }
            }
        } catch (e: Exception) {

            _userIdentify.value = RequestState.Error(e)

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


        //inset quiz

        InitQuiz.initQuiz()
        insertQuiz()

        //first user cheeks
        insertFirstUserCheeks()


    }


}