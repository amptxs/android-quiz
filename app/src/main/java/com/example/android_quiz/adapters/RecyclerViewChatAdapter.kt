package com.example.android_quiz.adapters

import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.android_quiz.R
import com.example.android_quiz.models.Message
import kotlinx.android.synthetic.main.cardview_message.view.*
import java.io.File

class RecyclerViewChatAdapter: RecyclerView.Adapter<RecyclerViewChatAdapter.ViewHolder>() {
    var items: MutableList<Message> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(model: Message) {
            itemView.apply{
                message_text.text = model.Text
                message_name.text = model.Name
                if (model.Image == null)
                    message_image.isGone = true
                else
                    message_image.setImageBitmap(MediaStore.Images.Media.getBitmap(context.contentResolver, Uri.fromFile(
                        File(model.Image)
                    )))
                message_image.clipToOutline = true
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.cardview_message, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }
    override fun getItemCount(): Int = items.size

    fun set(items: MutableList<Message>) {
        this.items = mutableListOf()
        this.items = items
    }

    fun add(message: Message){
        this.items.add(0, message)
    }

}