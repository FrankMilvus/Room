package com.example.room

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NoteDao) {
    val allNotes: LiveData<MutableList<Note>> = notesDao.getAllNotes()

    // For suspend operations (coroutine support)
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }


}
