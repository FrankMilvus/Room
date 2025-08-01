package com.example.room.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.room.Note
import com.example.room.NoteDataBase
import com.example.room.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<MutableList<Note>>
    val repository: NoteRepository

    init {
        val dao = NoteDataBase.getInstance(application).getNotesDoa()

        repository = NoteRepository(dao)
        allNotes = repository.allNotes


    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
     fun deleteAllNotes()=viewModelScope.launch {
        repository.deleteAllNotes()
    }


}