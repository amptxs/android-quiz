package com.example.android_quiz.controllers

import android.content.res.ColorStateList
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.example.android_quiz.MainActivity
import com.example.android_quiz.R
import com.example.android_quiz.models.Question
import kotlinx.android.synthetic.main.activity_main.*
import android.text.SpannableString
import android.text.Spannable
import android.text.style.AbsoluteSizeSpan

class QuizController {
    private val _parentActivity = MainActivity.instance!!
    private val _arrayOfAnswerButtons = arrayOf(_parentActivity.quiz_var_a, _parentActivity.quiz_var_b,
        _parentActivity.quiz_var_c,_parentActivity.quiz_var_d)
    private var _questionsList: MutableList<Question> = mutableListOf()
    private var _correctAnswers: Int = 0
    private var _currentQuestion: Question? = null
    private var _currentQuestionNumber: Int = 0


    fun bind(questionsList: MutableList<Question>) {
        _questionsList = questionsList
        firstStage()
        _parentActivity.quizActionButton.setOnClickListener {
                secondStage()
        }
    }

    private fun firstStage(){
        _parentActivity.quiz_answers.isVisible = false
        _parentActivity.quizProgressText.isGone = true
    }

    private fun secondStage(){
        _parentActivity.quiz_answers.isVisible = true
        _parentActivity.quizProgressText.isGone = false

        buttonInactiveCondition()
        _parentActivity.quizActionButton.text = _parentActivity.getString(R.string.proceed)

        if (_currentQuestionNumber != _questionsList.size)
            constructQuestionDisplay(_questionsList[_currentQuestionNumber])
        else
            thirdStage()
    }

    private fun thirdStage(){
        _parentActivity.quiz_answers.isGone = true
        _parentActivity.quizActionButton.isVisible = false

        _parentActivity.quizProgressText.background = null
        _parentActivity.quizProgressText.setTextColor(_parentActivity.getColor(R.color.white))

        val span: Spannable = SpannableString("\n🎉 \nПоздравляем!")
        span.setSpan(AbsoluteSizeSpan(160, true), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(AbsoluteSizeSpan(50, true), 5, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        _parentActivity.quizProgressText.text = span
        _parentActivity.quizProgressText.gravity = Gravity.CENTER

        var declension = "вопрос"
        if (_correctAnswers == 0 || _correctAnswers > 4)
            declension = "вопросов"
        else if (_correctAnswers in 2..4)
            declension = "вопроса"

        _parentActivity.quizMainText.text=
            "Вы ответили на " + _correctAnswers.toString()+" "+declension+" из "+_questionsList.size + ".\n" +
                    "Спасибо за проявленный интерес к нашей викторине!"
        _parentActivity.quizMainText.gravity = Gravity.CENTER

    }

    private fun constructQuestionDisplay(question: Question){
        _currentQuestion = question
        val number = _questionsList.indexOf(question) + 1
        _parentActivity.quizProgressText.text =
            _parentActivity.getString(R.string.question) + " "+ number + "/" + _questionsList.size

        _parentActivity.quizMainText.text = question.Label

        for (i in _arrayOfAnswerButtons.indices) {
            _arrayOfAnswerButtons[i].text = question.VarArray[i]
            _arrayOfAnswerButtons[i].setOnClickListener {
                answerCheck(_arrayOfAnswerButtons[i])
            }
        }
    }

    private fun answerCheck(view: View){
        _parentActivity.correctAnnotation.updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToEnd = view.id
            topToTop = view.id
            marginEnd = 6
        }

        if (_currentQuestion!!.VarArray.indexOf((view as Button).text) == _currentQuestion!!.Correct)
        {
            correctAnswer()
        }
        else
            incorrectAnswer()

        _currentQuestionNumber++
        buttonActiveCondition()
    }

    private fun buttonActiveCondition(){
        _parentActivity.quizActionButton.isEnabled = true
        _parentActivity.quizActionButton.setTextColor(_parentActivity.getColor(R.color.black))
        answersButtonInactiveCondition()
    }

    private fun buttonInactiveCondition(){
        _parentActivity.quizActionButton.isEnabled = false
        _parentActivity.quizActionButton.setTextColor(_parentActivity.getColor(R.color.element_inactive))
        answersButtonActiveCondition()
    }

    private fun answersButtonActiveCondition()
    {
        for (button in _arrayOfAnswerButtons)
            button.isEnabled = true

        _parentActivity.correctAnnotation.isVisible = false
    }
    private fun answersButtonInactiveCondition()
    {
        for (button in _arrayOfAnswerButtons)
            button.isEnabled = false

        _parentActivity.correctAnnotation.isVisible = true
    }

    private fun correctAnswer(){
        _correctAnswers++
        _parentActivity.correctAnnotation.backgroundTintList = ColorStateList.valueOf(_parentActivity.getColor(R.color.correct))
    }

    private fun incorrectAnswer(){
        _parentActivity.correctAnnotation.backgroundTintList = ColorStateList.valueOf(_parentActivity.getColor(R.color.incorrect))
    }
}