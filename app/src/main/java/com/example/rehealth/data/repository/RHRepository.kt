package com.example.rehealth.data.repository

import com.example.rehealth.data.MedicineDao
import com.example.rehealth.data.models.DrugReminder
import com.example.rehealth.data.models.DrugsClass
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.SideEffects
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.models.quiz.QuizClass
import com.example.rehealth.data.models.quiz.QuizResult
import com.example.rehealth.data.models.quiz.UserAnswer
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject

@ViewModelScoped
class RHRepository @Inject constructor(private val medicineDao: MedicineDao) {

    val getAllData: Flow<List<Medicines>> = medicineDao.getAllData()

    val getMedicineWithSideEffects: Flow<List<MedicineWithSideEffects>> =
        medicineDao.getMedicineWithSide()

    //insert data in medicines table....................
    suspend fun insertMedicines(medicines: Medicines) = medicineDao.insertMedicines(medicines)

    suspend fun insertSideEffects(sideEffects: SideEffects) =
        medicineDao.insertSideEffects(sideEffects)

    //insert Drug Reminder......................
    suspend fun insertDrugReminder(drugReminder: DrugReminder) =
        medicineDao.insertDrugReminder(drugReminder)

    //Read Drug Reminder
    val getAllDrugReminder: Flow<List<DrugReminder>> = medicineDao.getAllDrugReminder()

    //delete Drug Reminder
    suspend fun deleteDrugReminder(drugId: UUID) = medicineDao.deleteDrugReminder(drugId)


    //Drugs Class.........................
    suspend fun insertDrugs(drugsClass: DrugsClass) = medicineDao.insertDrugs(drugsClass)

    //read drugs
    fun getDrugs(timeShiftCode: Int): Flow<List<DrugsClass>> =
        medicineDao.getAllDrugs(timeShiftCode)

    //delete one drug
    suspend fun deleteDrug(drugId: Int) = medicineDao.deleteDrug(drugId)

    //delete shift Drugs
    suspend fun deleteShiftDrug(shiftId: UUID) = medicineDao.deleteShiftDrug(shiftId)


    //Tests functions........................

    //insert
    suspend fun insertTestsReminder(testReminder: TestReminder) =
        medicineDao.insertTestsReminder(testReminder)

    //read
    val getAllTests: Flow<List<TestReminder>> = medicineDao.getAllTests()

    //delete one test
    suspend fun deleteTest(testId:UUID) = medicineDao.deleteTest(testId)


    // Visit Functions....................................
    suspend fun insertVisitsReminder(visitReminder: VisitReminder) =
        medicineDao.insertVisitReminder(visitReminder)

    //read
    val getAllVisits: Flow<List<VisitReminder>> = medicineDao.getAllVisitReminder()

    //delete one test
    suspend fun deleteVisit(visitId:UUID) = medicineDao.deleteVisitReminder(visitId)


    //Quiz Functions...................................
    //insert
    suspend fun insertQuiz(quizClass: QuizClass) = medicineDao.insertQuiz(quizClass)

    //read Quiz
    fun getQuiz(type:Int) = medicineDao.getQuiz(type)

    //insert user Answer
    suspend fun insertUserAnswer(userAnswer: UserAnswer) = medicineDao.insertUserAnswer(userAnswer)


    //Quiz Result for doctor advice
    suspend fun insertFirstUserCheeks(quizResult: QuizResult)=medicineDao.insertFirstUserCheeks(quizResult)
    suspend fun updateQuizResult1(userCheek:Int) = medicineDao.updateQuizResult1(userCheek)
    suspend fun updateQuizResult2(userCheek:Int) = medicineDao.updateQuizResult2(userCheek)

    val readUserCheeks:Flow<QuizResult> = medicineDao.readQuizCheeks()

}