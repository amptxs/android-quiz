package com.example.android_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android_quiz.controllers.QuizController
import com.example.android_quiz.models.Question
import kotlinx.android.synthetic.main.activity_main.*


import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.example.android_quiz.fragments.ChatFragment
import com.example.android_quiz.fragments.QuizFragment

import com.google.android.material.bottomnavigation.BottomNavigationView




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

        bottomNavView.selectedItemId = R.id.nav_quiz

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, chatFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, quizFragment).commit()
        bottomNavView.setOnNavigationItemSelectedListener(navListener);


    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            var unselectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_chat -> {
                    selectedFragment = chatFragment
                    unselectedFragment = quizFragment
                }
                R.id.nav_quiz -> {
                    selectedFragment = quizFragment
                    unselectedFragment = chatFragment
                }
            }
            supportFragmentManager.beginTransaction().hide(
                unselectedFragment!!
            ).commit()
            supportFragmentManager.beginTransaction().show(
                selectedFragment!!
            ).commit()
            true
        }
}