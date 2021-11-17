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

        var test1 = Question()
        var test2 = Question()
        test1.Label = "В каком городе лисицы живут на окраинах и иногда появляются в центре?"
        test2.Label = "test2"
        QuestionsList.add(test1)
        QuestionsList.add(test2)

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