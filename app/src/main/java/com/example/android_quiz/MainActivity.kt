package com.example.android_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.Fragment
import com.example.android_quiz.fragments.ChatFragment
import com.example.android_quiz.fragments.QuizFragment


class MainActivity : AppCompatActivity() {

    private val quizFragment by lazy{
        QuizFragment()
    }
    private val chatFragment by lazy{
        ChatFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNavigation()
    }

    private fun setNavigation(){
        bottomNavView.selectedItemId = R.id.nav_quiz

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, chatFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, quizFragment).commit()

        bottomNavView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_chat -> {
                    selectedFragment = chatFragment
                    title = resources.getString(R.string.chat)
                }
                R.id.nav_quiz -> {
                    selectedFragment = quizFragment
                    title = resources.getString(R.string.quiz)
                }
            }

            for (frag in supportFragmentManager.fragments)
                supportFragmentManager.beginTransaction().hide(frag).commit()

            supportFragmentManager.beginTransaction().show(selectedFragment!!).commit()

            true
        }
    }


}