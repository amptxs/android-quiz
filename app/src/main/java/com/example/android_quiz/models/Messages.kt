package com.example.android_quiz.models

import android.graphics.Bitmap

class Message(name: String, text: String, image: Bitmap?) {
    constructor() : this("Name Surname", "Sample text", null)

    var Name = name
    var Text = text
    var Image = image

}