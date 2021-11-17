package com.example.android_quiz.models

import android.graphics.Bitmap
import java.io.Serializable

class Message(name: String, text: String, image: Bitmap?) : Serializable{
    constructor() : this("Name Surname", "Sample text", null)

    var Name = name
    var Text = text
    var Image = image

}