package com.example.android_quiz.controllers

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.example.android_quiz.R
import com.example.android_quiz.models.Question
import android.text.SpannableString
import android.text.Spannable
import android.text.style.AbsoluteSizeSpan
import com.example.android_quiz.fragments.QuizFragment
import kotlinx.android.synthetic.main.fragment_quiz.view.*

class QuizController {
    private val _parentActivity: View = QuizFragment.RootView!!
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

        actionButtonCondition(false)
        _parentActivity.quizActionButton.text = _parentActivity.resources.getString(R.string.proceed)

        if (_currentQuestionNumber != _questionsList.size)
            constructQuestionDisplay(_questionsList[_currentQuestionNumber])
        else
            thirdStage()
    }

    private fun thirdStage(){
        _parentActivity.quiz_answers.isGone = true
        _parentActivity.quizActionButton.isVisible = false

        _parentActivity.quizProgressText.background = null
        _parentActivity.quizProgressText.setTextColor(_parentActivity.resources.getColor(R.color.white))

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
            _parentActivity.resources.getString(R.string.question) + " "+ number + "/" + _questionsList.size

        _parentActivity.quizMainText.text = question.Label

        for (i in _arrayOfAnswerButtons.indices) {
            _arrayOfAnswerButtons[i].text = question.VarArray[i]
            setButtonDrawable(_arrayOfAnswerButtons[i], R.drawable.shape_round20dp)
            _arrayOfAnswerButtons[i].setOnClickListener {
                answerCheck(_arrayOfAnswerButtons[i])
            }
        }
    }

    private fun answerCheck(view: View){
        _parentActivity.correctAnnotation.updateLayoutParams<ConstraintLayout.LayoutParams> {
            endToEnd = view.id
            topToTop = view.id
        }

        _currentQuestionNumber++

        val isCorrect = _currentQuestion!!.isCorrect((view as Button).text.toString())

        if (isCorrect) {
            _correctAnswers++
            _parentActivity.correctAnnotation.backgroundTintList =
                ColorStateList.valueOf(_parentActivity.resources.getColor(R.color.correct))
            setButtonDrawable(view, R.drawable.shape_round20dp_correct)
        }
        else {
            _parentActivity.correctAnnotation.backgroundTintList =
                ColorStateList.valueOf(_parentActivity.resources.getColor(R.color.incorrect))
            setButtonDrawable(view, R.drawable.shape_round20dp_incorrect)
        }
        actionButtonCondition(true)
    }

    private fun actionButtonCondition(active: Boolean){
        _parentActivity.quizActionButton.isEnabled = active
        if (active)
            _parentActivity.quizActionButton.setTextColor(_parentActivity.resources.getColor(R.color.black))
        else
            _parentActivity.quizActionButton.setTextColor(_parentActivity.resources.getColor(R.color.element_inactive))
        answersButtonsCondition(!active)
    }

    private fun answersButtonsCondition(active: Boolean)
    {
        for (button in _arrayOfAnswerButtons)
            button.isEnabled = active
        _parentActivity.correctAnnotation.isVisible = !active
    }

    private fun setButtonDrawable(view: View, resource:Int)
    {
        ((view as Button).setBackgroundResource(resource))
    }



}