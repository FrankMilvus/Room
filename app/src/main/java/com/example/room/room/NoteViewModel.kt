package com.example.room.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.room.Note
import com.example.room.NoteDataBase
import com.example.room.NoteRepository

class NoteViewModel (application: Application) : AndroidViewModel(application){
    val allNotes: LiveData<MutableList<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDataBase.getInstance(application).getNotesDoa()
    }
}