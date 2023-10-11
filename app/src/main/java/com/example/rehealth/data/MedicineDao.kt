package com.example.rehealth.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.rehealth.data.models.drug.DrugReminder
import com.example.rehealth.data.models.drug.DrugsClass
import com.example.rehealth.data.models.MedicineWithSideEffects
import com.example.rehealth.data.models.Medicines
import com.example.rehealth.data.models.QuizReminder
import com.example.rehealth.data.models.SideEffects
import com.example.rehealth.data.models.TestReminder
import com.example.rehealth.data.models.UserIdentification
import com.example.rehealth.data.models.VisitReminder
import com.example.rehealth.data.models.drug.ReminderWithDrugs
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
    @Query("select * from DrugReminder")
    fun getAllDrugReminder(): Flow<List<DrugReminder>>

    //delete one Drug Reminder
    @Query("delete from DrugReminder where reminderId=:drugId")
    suspend fun deleteDrugReminder(drugId: UUID)


    //Drugs Class............................................
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugs(drugsClass: DrugsClass)

    //read drugs and drug reminder
    @Transaction
    @Query("select * from DrugReminder where reminderId=:reminderId ")
    fun getReminderWithDrugs(reminderId: UUID): Flow<ReminderWithDrugs>

    @Transaction
    @Query("select * from DrugReminder where shiftCode=:shiftCode ")
    fun getReminderWithDrugsByShift(shiftCode: Int): Flow<ReminderWithDrugs?>

    //getAllDrugsById
    /*@Query("select * from DrugsClass where shiftId=:drugId order by id")
    fun getAllDrugsById(drugId:UUID): Flow<List<DrugsClass>>*/


    //Delete one Drug
    @Query("delete from DrugsClass where drugId=:drugId")
    suspend fun deleteDrug(drugId: Int)

    //delete all drug in shift
    /*@Query("delete from DrugsClass where shiftId=:shiftId")
    suspend fun deleteShiftDrug(shiftId: UUID)*/


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

    //test
    @Query("update TestReminder set userAssociation=1 where alarmId=:alarmId")
    suspend fun updateTestAssociation(alarmId: Int)

    @Query("select count(userAssociation) from TestReminder where userAssociation=1")
    fun getTestAssociation(): Flow<Int>

    @Query("select count(*) from TestReminder")
    fun getTestSize(): Flow<Int>

    //visit
    @Query("update VisitReminder set userAssociation=1 where alarmId=:alarmId")
    suspend fun updateVisitAssociation(alarmId: Int)

    @Query("select count(userAssociation) from VisitReminder where userAssociation=1")
    fun getVisitAssociation(): Flow<Int>

    @Query("select count(*) from VisitReminder")
    fun getVisitSize(): Flow<Int>

    //drug
    @Query("update DrugReminder set userAssociation=(userAssociation+1) where alarmId=:alarmId")
    suspend fun updateDrugAssociation(alarmId: Int)

    /*@Query("update Drug_Reminder set notificationCount=(notificationCount+1) where alarmId=:alarmId")
    suspend fun updateDrugNotification(alarmId: Int)*/


    //Quiz Reminder.......................................
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizReminder(quizReminder: QuizReminder)

    //read
    @Query("select * from QuizReminder")
    fun getAllQuizReminder(): Flow<List<QuizReminder>>

    //delete one Drug Reminder
    @Query("delete from QuizReminder where quizId=:quizId")
    suspend fun deleteQuizReminder(quizId: Int)

    //user Identification
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserIdentification(userIdentification: UserIdentification)

    @Query("select * from UserIdentification where id=0")
    fun readUserIdentification(): Flow<UserIdentification?>
}