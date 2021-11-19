package com.example.android_quiz.DAO

import androidx.room.*
import com.example.android_quiz.models.Message

@Dao
interface MessageDAO {

    @Query("SELECT * FROM Message")
    fun getAll(): List<Message>

    @Query("SELECT * FROM Message ORDER BY uid DESC LIMIT :count")
    fun getFromTail(count: Int): List<Message>

    @Query("SELECT * FROM Message WHERE uid BETWEEN :start AND :end ORDER BY uid DESC")
    fun getByPosition(start: Int, end: Int): List<Message>

    @Insert
    fun insertAll(vararg message: Message)

    @Delete
    fun delete(user: Message)

}
