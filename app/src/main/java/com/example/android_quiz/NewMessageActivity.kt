package com.example.android_quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_new_message.*
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.example.android_quiz.models.Message

const val EXTRA_MESSAGE_BACK = "Message_object"
const val EXTRA_URI_BACK = "URI"

class NewMessageActivity : AppCompatActivity() {

    private val PICK_IMAGE = 100
    private var uri: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)

        supportActionBar!!.setHomeAsUpIndicator(resources.getDrawable(R.drawable.ic_close))
        title = resources.getString(R.string.your_message)
        sendMessageButtonEnabled(false)

        for (editable in arrayOf(editText_Name, editText_Message))
            editable?.doOnTextChanged { text, start, before, count ->
                sendMessageButtonEnabled(editText_Name.text.isNotEmpty() && editText_Message.text.isNotEmpty())
            }

        button_AttachImage.setOnClickListener{
            openGallery()
        }

        button_sendMessage.setOnClickListener {
            backIntent()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onBackPressed()
        setResult(0, null)
        return true
    }

    private fun sendMessageButtonEnabled(condition: Boolean){
        button_sendMessage.isEnabled = condition
        when {
            condition -> button_sendMessage.setTextColor(resources.getColor(R.color.black))
            else -> button_sendMessage.setTextColor(resources.getColor(R.color.element_inactive))
        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            uri = data?.data
            imageView_newMessage.setImageBitmap(MediaStore.Images.Media.getBitmap(this.contentResolver, uri))
            imageView_newMessage.clipToOutline = true
        }
    }

    private fun backIntent(){
        val intentBack = Intent().putExtra(EXTRA_MESSAGE_BACK,
            Message(editText_Name.text.toString(), editText_Message.text.toString(), null))
            .putExtra(EXTRA_URI_BACK, uri)
        setResult(1, intentBack)
        finish()
    }
}