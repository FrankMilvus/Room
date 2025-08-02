package com.example.room

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room.activities.AddEditActivity
import com.example.room.adaptors.NoteAdapter
import com.example.room.room.NoteViewModel
import com.example.room.utils.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var notesAdapter: NoteAdapter
    private lateinit var addNoteButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addNoteButton = findViewById(R.id.add_note_button)
        recyclerView = findViewById(R.id.recycler_view)
        notesAdapter = NoteAdapter()
        recyclerView.adapter = notesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[NoteViewModel::class.java]

        noteViewModel.allNotes.observe(this) { it ->
            notesAdapter.setNotes(it)
        }

        val getResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Constants.REQUEST_CODE) {
                    val title = it.data?.getStringExtra(Constants.EXTRA_TITLE)
                    val description = it.data?.getStringExtra(Constants.EXTRA_DESCRIPTION)
                    val priority = it.data?.getIntExtra(Constants.EXTRA_PRIORITY, -1)

                    val note = Note(title!!, description!!, priority!!)
                    noteViewModel.addNote(note)

                }
            }

        addNoteButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditActivity::class.java)
            getResult.launch(intent)
        }

        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
                noteViewModel.deleteNote(notesAdapter.getNoteAt(viewHolder.adapterPosition))
            }

        }).attachToRecyclerView(recyclerView)





    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_notes_menu -> {
                noteViewModel.deleteAllNotes()

            }

        }
        return super.onOptionsItemSelected(item)
    }
}














