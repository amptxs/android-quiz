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

    override fun onStart() {
        super.onStart()

        setFragment(chatFragment)
    }

    private fun setNavigation(){
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, quizFragment).commit()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, chatFragment).commit()

        bottomNavView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null

            when (item.itemId) {
                R.id.nav_chat -> selectedFragment = chatFragment
                R.id.nav_quiz -> selectedFragment = quizFragment
            }

            setFragment(selectedFragment!!)
            true
        }
    }

    private fun setFragment(fragment: Fragment){
        for (frag in supportFragmentManager.fragments)
            supportFragmentManager.beginTransaction().hide(frag).commit()

        supportFragmentManager.beginTransaction().show(fragment!!).commit()
    }



}