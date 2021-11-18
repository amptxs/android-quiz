package com.example.android_quiz.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.android_quiz.models.Message

@Dao
interface MessageDAO {
    @Query("SELECT * FROM Message")
    fun getAll(): List<Message>

    @Insert
    fun insertAll(vararg message: Message)

    @Delete
    fun delete(user: Message)
}