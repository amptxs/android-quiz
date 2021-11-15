package com.example.android_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.android_quiz.controllers.QuizController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quizController by lazy{
        QuizController()
    }
    companion object{
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instance = this

        bottomNavView.selectedItemId = R.id.nav_quiz
        quizController.bind()

    }

    fun getInstance(): MainActivity? {
        return instance
    }
}