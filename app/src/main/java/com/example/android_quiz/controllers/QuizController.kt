package com.example.android_quiz.controllers

import android.app.Activity
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.android_quiz.MainActivity
import com.example.android_quiz.R
import com.example.android_quiz.models.Question
import kotlinx.android.synthetic.main.activity_main.*

class QuizController() {
    private val ParentActivity = MainActivity.instance!!
    private val ArrayOfAnswerButtons = arrayOf(ParentActivity.quiz_var_a, ParentActivity.quiz_var_b,
        ParentActivity.quiz_var_c,ParentActivity.quiz_var_d)

    private var Stage: Int = 0
    private var QuestionsList: MutableList<Question> = mutableListOf()
    private var CorrectAnswers: Int = 0
    private var CurrentQuestion: Question? = null
    private var CurrentQuestionNumber: Int = 0


    fun bind() {
        firstStage()

        var test1 = Question()
        var test2 = Question()
        test1.Label = "test1"
        test2.Label = "test2"
        QuestionsList.add(test1)
        QuestionsList.add(test2)

        ParentActivity.quizActionButton.setOnClickListener {
                secondStage()
        }
    }

    fun firstStage(){
        ParentActivity.quiz_answers.isVisible = false
    }

    fun secondStage(){
        ParentActivity.quiz_answers.isVisible = true
        buttonInactiveCondition()
        ParentActivity.quizActionButton.text = ParentActivity.getString(R.string.proceed)

        if (CurrentQuestionNumber != QuestionsList.size)
            constructQuestionDisplay(QuestionsList[CurrentQuestionNumber])
        else
            thirdStage()
    }

    fun thirdStage(){
        ParentActivity.quizMainText.text = CorrectAnswers.toString()+"/"+QuestionsList.size
        ParentActivity.quiz_answers.isVisible = false
        ParentActivity.quizActionButton.isVisible = false
    }

    fun constructQuestionDisplay(question: Question){
        CurrentQuestion = question
        ParentActivity.quizMainText.text = question.Label

        for (i in ArrayOfAnswerButtons.indices) {
            ArrayOfAnswerButtons[i].text = question.VarArray[i]
            ArrayOfAnswerButtons[i].setOnClickListener {

                answerCheck(ArrayOfAnswerButtons[i])
            }
        }
    }

    fun answerCheck(view: View){

        if (CurrentQuestion!!.VarArray.indexOf((view as Button).text) == CurrentQuestion!!.Correct)
            CorrectAnswers++

        CurrentQuestionNumber++
        buttonActiveCondition()

    }

    fun buttonActiveCondition(){
        ParentActivity.quizActionButton.isEnabled = true
        ParentActivity.quizActionButton.setTextColor(ParentActivity.getColor(R.color.black))
        answersButtonInactiveCondition()
    }

    fun buttonInactiveCondition(){
        ParentActivity.quizActionButton.isEnabled = false
        ParentActivity.quizActionButton.setTextColor(ParentActivity.getColor(R.color.element_inactive))
        answersButtonActiveCondition()
    }

    fun answersButtonActiveCondition()
    {
        for (button in ArrayOfAnswerButtons)
            button.isEnabled = true;
    }
    fun answersButtonInactiveCondition()
    {
        for (button in ArrayOfAnswerButtons)
            button.isEnabled = false;
    }

}