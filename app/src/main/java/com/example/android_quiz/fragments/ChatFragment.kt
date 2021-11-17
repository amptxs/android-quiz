package com.example.android_quiz.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_quiz.NewMessageActivity
import com.example.android_quiz.R
import com.example.android_quiz.adapters.RecyclerViewChatAdapter
import com.example.android_quiz.models.Message
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {

    companion object{
        var RootView: View? = null
    }

    private val recyclerAdapter by lazy{
        RecyclerViewChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        RootView = inflater.inflate(R.layout.fragment_chat, container, false)
        return RootView
    }

    override fun onStart() {
        super.onStart()
        chatContainer.apply {
            adapter= recyclerAdapter
            layoutManager= LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
        }

        recyclerAdapter.add(Message("Иван Иванович", "Лиси́ца — это хищное млекопитающее, относится к отряду хищные, семейству псовые. Латинское название рода лисицы, по всей видимости, произошло от искаженных слов: латинского «lupus» и немецкого «Wolf»", null))
        recyclerAdapter.add(Message("Сергей Сергеевич", "Лисица распространена весьма широко: на всей территории Европы, Северной Африки (Египет, Алжир, Марокко, северный Тунис), большей части Азии (вплоть до северной Индии, южного Китая и Индокитая), в Северной Америке от арктической зоны до северного побережья Мексиканского залива.", null))
        recyclerAdapter.add(Message("Александр Александрович", "Окраска и размеры лисиц различны в разных местностях; всего насчитывают 40 — 50 подвидов, не учитывая более мелких форм.", BitmapFactory.decodeResource(getResources(),R.drawable.image_fox)))

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden)
            (requireActivity() as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.chat)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        (requireActivity() as AppCompatActivity).menuInflater.inflate(R.menu.chat_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.newMessageButton -> {
                val intent = Intent(context, NewMessageActivity::class.java)
                ActivityCompat.startActivityForResult(activity as Activity, intent, 1, null)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}