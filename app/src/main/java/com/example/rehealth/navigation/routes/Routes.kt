package com.example.rehealth.navigation.routes

object Routes {

    //Notification
    const val TestNotification = "https://TestHomeScreen.com"
    const val VisitNotification = "https://VisitHomeScreen.com"
    const val DrugNotification = "https://DrugHomeScreen.com"
    const val Message = "Message"


    //Test
    const val TestListScreenRoute = "TestListScreenRoute"
    const val TestAddScreenRoute = "TestAddScreenRoute/{TestId}"


    //visit
    const val VisitListScreenRoute = "VisitListScreenRoute"
    const val VisitAddScreenRoute = "VisitAddScreenRoute/{VisitId}"


    //Drug
    const val DrugListScreenRoute = "DrugListScreenRoute"
    const val DrugAddScreenRoute = "DrugAddScreenRoute/{DrugId}"

    //Homessss...........................
    const val VisitHomeScreenRoute = "VisitHomeScreenRoute"
    const val DrugHomeScreenRoute = "DrugHomeScreenRoute"
    const val TestHomeScreenRoute = "TestHomeScreenRoute/{$Message}"
    const val QuizHomeScreenRoute = "QuizHomeScreenRoute"

    const val QuestionsScreenRoute = "QuestionsScreenRoute/{quizType}"
    const val QuestionDoneScreenRoute = "QuestionDoneScreenRoute"



}