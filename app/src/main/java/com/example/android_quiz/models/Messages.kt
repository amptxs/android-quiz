package com.example.android_quiz.models

import android.graphics.Bitmap
import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Message(
    @PrimaryKey var uid: Int?,
    @Ignore val messageName: String,
    @Ignore val messageText: String,
    @Ignore val messageImage: String?) : Serializable{
    constructor() : this(null,"Name Surname", "Sample text", null)

    @ColumnInfo(name = "message_name") var Name = messageName
    @ColumnInfo(name = "message_text") var Text = messageText
    @ColumnInfo(name = "message_image") var Image = messageImage

}