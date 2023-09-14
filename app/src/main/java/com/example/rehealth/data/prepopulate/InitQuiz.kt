package com.example.rehealth.data.prepopulate

import com.example.rehealth.data.models.quiz.QuizClass

object InitQuiz {

    private val _listOfQuiz: MutableList<QuizClass> = ArrayList()
    val listOfQuiz : List<QuizClass> = _listOfQuiz


     fun initQuiz(): MutableList<QuizClass> {


        ////Quiz Type A
        _listOfQuiz.add(
            QuizClass(
                1,
                1,
                "آیا نسبت به روزهای معمول، احساس میکنید انرژیتان بیشتر شده است؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                2,
                1,
                "آیا نسبت به روزهای معمول، کارهای بیشتری انجام میدهید؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                3,
                1,
                "آیا نسبت به روزهای معمول، نسبت به خودتان، حس اطمینان و اعتماد بیشتری دارید؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                4,
                1,
                "آیا نسبت به روزهای معمول، بیشتر حرف میزنید و اطرافیان از این موضوع گله مند هستند؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                5,
                1,
                "آیا نسبت به روزهای معمول، صداها و موضوع های اطراف، به راحتی حواس شما را پرت میکند؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                6,
                1,
                "آیا نسبت به روزهای معمول، نیاز به خواب شما کمتر شده است؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                7,
                1,
                "آیا به خودکشی فکر میکنید؟",
                "بله",
                "خیر"
            )
        )

        //Quiz Type B
        _listOfQuiz.add(
            QuizClass(
                8,
                2,
                "آیا نسبت به زندگی بی علاقه شده اید و دیگر هیچ موردی برای شما لذت بخش نمی باشد؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                9,
                2,
                "آیا نسبت به روزهای معمول، خواب شما تغییر کرده است؟",
                "بله (بیشترشده/ کمتر شده)",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                10,
                2,
                "آیا نسبت به روزهای معمول، اشتهای شما تغییر کرده است؟",
                "بله (بیشترشده/ کمتر شده)",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                11,
                2,
                "آیا نسبت به روزهای معمول، احساس میکنید که توانایی فکر شما کاهش یافته و نمیتوانید تمرکز کنید؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                12,
                2,
                "آیا نسبت به روزهای معمول، احساس بی ارزشی یا گناه نامتناسب دارید؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                13,
                2,
                "آیا نسبت به روزهای معمول، احساس خستگی میکنید؟",
                "بله",
                "خیر"
            )
        )
        _listOfQuiz.add(
            QuizClass(
                14,
                2,
                "آیا به خودکشی فکر میکنید؟",
                "بله",
                "خیر"
            )
        )



        return _listOfQuiz
    }
}