package com.example.android_quiz.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_quiz.*
import com.example.android_quiz.adapters.RecyclerViewChatAdapter
import com.example.android_quiz.models.Message
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {

    private val recyclerAdapter by lazy{
        RecyclerViewChatAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        recyclerAdapter.add(Message("Иван Иванович", "Лиси́ца — это хищное млекопитающее, относится к отряду хищные, семейству псовые. Латинское название рода лисицы, по всей видимости, произошло от искаженных слов: латинского «lupus» и немецкого «Wolf»", null))
        recyclerAdapter.add(Message("Сергей Сергеевич", "Лисица распространена весьма широко: на всей территории Европы, Северной Африки (Египет, Алжир, Марокко, северный Тунис), большей части Азии (вплоть до северной Индии, южного Китая и Индокитая), в Северной Америке от арктической зоны до северного побережья Мексиканского залива.", null))
        recyclerAdapter.add(Message("Александр Александрович", "Окраска и размеры лисиц различны в разных местностях; всего насчитывают 40 — 50 подвидов, не учитывая более мелких форм.", BitmapFactory.decodeResource(getResources(),R.drawable.image_fox)))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_chat, container, false)
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
                requireActivity().startActivityFromFragment(this, intent, 1)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            1 -> {
                val message = data?.getSerializableExtra(EXTRA_MESSAGE_BACK) as Message
                val uri = data?.extras!!.get(EXTRA_URI_BACK) as Uri
                message.Image =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                recyclerAdapter.add(message)
            }
        }
    }

}