package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.*

//DAO (Data Access Object) is a design pattern used in Android (with Room Database)
//to separate database operations from the rest of the app. It provides a clean API for reading,
//inserting, updating, and deleting data without writing raw SQL queries everywhere.

interface NoteDao{
    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY priority ASC")
    fun getAllNotes(): LiveData<MutableList<Note>> //real time updates of all
}