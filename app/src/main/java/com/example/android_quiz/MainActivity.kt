package com.example.android_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_quiz.controllers.QuizController
import com.example.android_quiz.models.Question
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizController by lazy{
        QuizController()
    }
    companion object{
        var instance: MainActivity? = null
        var QuestionsList: MutableList<Question> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this
        bottomNavView.selectedItemId = R.id.nav_quiz

        var test1 = Question()
        var test2 = Question()
        test1.Label = "В каком городе лисицы живут на окраинах и иногда появляются в центре?"
        test2.Label = "test2"
        QuestionsList.add(test1)
        QuestionsList.add(test2)
        quizController.bind(QuestionsList)
    }
}