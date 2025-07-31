package com.example.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//DAO (Data Access Object) is a design pattern used in Android (with Room Database)
//to separate database operations from the rest of the app. It provides a clean API for reading,
//inserting, updating, and deleting data without writing raw SQL queries everywhere.

interface NoteDao{
    @Insert
    suspend insert(note: Note)

    @Delete
    suspend delete(note: Note)

    @Update
    suspend update(note: Note)

    @Query("DELETE * FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM NOTE_TABLE ORDER BY priority ASC")
    fun getAllNotes(): LiveData<MutableList<Note>> //real time updates of all
}