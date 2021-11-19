package com.example.android_quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.android_quiz.MainActivity
import com.example.android_quiz.R
import com.example.android_quiz.controllers.QuizController
import com.example.android_quiz.models.Question



class QuizFragment : Fragment() {

    companion object{
        var QuestionsList: MutableList<Question> = mutableListOf()
        var RootView: View? = null
    }

    private val quizController by lazy {
        QuizController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

        var quizList = listOf<Question>(
            Question("В каком городе лисицы живут на окраинах и иногда появляются в центре?", arrayOf("Архангельск", "Тюмень", "Рим", "Лондон"), 0),
            Question("К какому семейству относятся лисы?", arrayOf("Псовые", "Лисьи", "Волчьи", "Собачьи"), 0),
            Question("Какого цвета лапы у обыкновенной лисицы?", arrayOf("Рыжие", "Белые", "Темные", "Бурые"), 2),
            Question("На какое животное внешне и по поведению похожа афганская лисица?", arrayOf("Собака", "Волк", "Кошка", "Куница"), 2),
            Question("Какая лисица обитает в предгорьях Южных Гималаев?", arrayOf("Бенгальская", "Корсак", "Тибетская ", "Песчаная"), 0),
        )
        for (quiz in quizList)
            QuestionsList.add(quiz)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        RootView = inflater.inflate(R.layout.fragment_quiz, container, false)

        quizController.bind(QuestionsList)

        return RootView
    }


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden)
            (requireActivity() as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.quiz)
    }

}