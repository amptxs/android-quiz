package com.example.android_quiz.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_quiz.MainActivity
import com.example.android_quiz.R
import com.example.android_quiz.controllers.QuizController
import com.example.android_quiz.models.Question

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    companion object{
        var QuestionsList: MutableList<Question> = mutableListOf()
        var RootView: View? = null
    }

    private val quizController by lazy {
        QuizController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            quizController.bind(QuestionsList)
        }

        var test1 = Question()
        var test2 = Question()
        test1.Label = "В каком городе лисицы живут на окраинах и иногда появляются в центре?"
        test2.Label = "test2"
        QuestionsList.add(test1)
        QuestionsList.add(test2)

        getString(R.string.proceed)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView = inflater.inflate(R.layout.fragment_quiz, container, false)
        RootView = rootView

        quizController.bind(QuestionsList)

        return rootView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)


    }

}