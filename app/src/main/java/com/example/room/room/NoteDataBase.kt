package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
annotation class NoteDataBase : RoomDatabase() {
    abstract fun getNotesDoa(): NoteDao

    companion object {
        private var INSTANCE: NoteDataBase? = null

        fun getInstance(context: Context) {
            //if instance is null, then return it
            //if it is null then create DB
            return INSTANCE ?: synchronized(this)
            {
                val instance= Room.databaseBuilder(context.applicationContext,
                    NoteDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance

                instance

            }
        }
    }
}