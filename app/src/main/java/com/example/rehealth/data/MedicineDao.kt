package com.example.rehealth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
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
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: Medicines)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSideEffects(sideEffects: SideEffects)

    @Query("select * from medicines order by medicineId ASC")
    fun getAllData(): Flow<List<Medicines>>

    @Transaction
    @Query("select * from medicines order by medicineId ASC")
    fun getMedicineWithSide(): Flow<List<MedicineWithSideEffects>>


    //Drug Reminder.......................................
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugReminder(drugReminder: DrugReminder)

    //read
    @Query("select * from Drug_Reminder")
    fun getAllDrugReminder(): Flow<List<DrugReminder>>

    //delete one Drug Reminder
    @Query("delete from Drug_Reminder where drugId=:drugId")
    suspend fun deleteDrugReminder(drugId: UUID)


    //Drugs Class............................................
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugs(drugsClass: DrugsClass)

    //read
    @Query("select * from DrugsClass where shiftTime=:timeShiftCode order by id")
    fun getAllDrugs(timeShiftCode: Int): Flow<List<DrugsClass>>

    //Delete one Drug
    @Query("delete from DrugsClass where id=:drugId")
    suspend fun deleteDrug(drugId: Int)

    //delete all drug in shift
    @Query("delete from DrugsClass where shiftId=:shiftId")
    suspend fun deleteShiftDrug(shiftId: UUID)


    //Test Reminder class...............................

    //insertTests Reminder
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTestsReminder(testReminder: TestReminder)

    //read Tests Reminder
    @Query("select * from TestReminder")
    fun getAllTests(): Flow<List<TestReminder>>

    //delete one test
    @Query("delete from TestReminder where testId=:testId")
    suspend fun deleteTest(testId: UUID)


    //Visit Reminder.......................................
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisitReminder(visitReminder: VisitReminder)

    //read
    @Query("select * from VisitReminder")
    fun getAllVisitReminder(): Flow<List<VisitReminder>>

    //delete one Drug Reminder
    @Query("delete from VisitReminder where visitId=:visitId")
    suspend fun deleteVisitReminder(visitId: UUID)


    //Quiz Functions.............................................

    //insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuiz(quizClass: QuizClass)

    //read quiz
    @Query("select * from QuizClass where type=:typeQuiz")
    fun getQuiz(typeQuiz: Int): Flow<List<QuizClass>>

    //insert User ANSWER
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserAnswer(userAnswer: UserAnswer)

    //Quiz Result for doctor advice
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFirstUserCheeks(quizResult: QuizResult)

    @Query("update QuizResult set userCheek1=:userCheek where id=0")
    suspend fun updateQuizResult1(userCheek: Int)

    @Query("update QuizResult set userCheek2=:userCheek where id=0")
    suspend fun updateQuizResult2(userCheek: Int)


    @Query("select * from QuizResult where id=0")
    fun readQuizCheeks(): Flow<QuizResult>


    //user Association....................................

    // drug..
    @Query("update Drug_Reminder set userAssociation=:userAssociation where alarmId=:alarmId")
    suspend fun updateDrugAssociation(alarmId: Int, userAssociation: Int)

    //visit
    @Query("update VisitReminder set userAssociation=:userAssociation where alarmId=:alarmId")
    suspend fun updateVisitAssociation(alarmId: Int,userAssociation: Boolean)

    //test
    @Query("update TestReminder set userAssociation=:userAssociation where alarmId=:alarmId")
    suspend fun updateTestAssociation(alarmId: Int,userAssociation: Boolean)

}