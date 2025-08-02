package com.example.room.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.room.Note
import com.example.room.R

class NoteAdapter() :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private var notesList: MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewPriority.text = note.priority.toString()
    }


    override fun getItemCount() = notesList.size

    fun setNotes(notes: MutableList<Note>) {
        notesList = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): Note {
        return notesList[position]
    }

    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTitle = view.findViewById(R.id.tv_title) as TextView
        val textViewDescription = view.findViewById(R.id.tv_description) as TextView
        val textViewPriority = view.findViewById(R.id.tv_priority) as TextView
    }
}


