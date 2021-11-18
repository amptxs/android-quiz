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
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android_quiz.*
import com.example.android_quiz.DAO.MessageDAO
import com.example.android_quiz.adapters.RecyclerViewChatAdapter
import com.example.android_quiz.models.Message
import kotlinx.android.synthetic.main.fragment_chat.*


class ChatFragment : Fragment() {

    private val recyclerAdapter by lazy{
        RecyclerViewChatAdapter()
    }
    private val dataBase by lazy {
        Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
        //fallbackToDestructiveMigration()
    }

    @Database(entities = [Message::class], version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun messageDAO(): MessageDAO
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val messages = dataBase.messageDAO().getAll()
        for (message in messages)
            recyclerAdapter.add(message)
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
                if (data?.extras!!.get(EXTRA_URI_BACK) != null) {
                    message.Image = data?.extras!!.get(EXTRA_URI_BACK) as String
                }
                recyclerAdapter.add(message)
                dataBase.messageDAO().insertAll(message)
            }
        }
    }

}