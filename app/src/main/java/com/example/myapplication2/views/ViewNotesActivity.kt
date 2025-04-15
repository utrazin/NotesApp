package com.example.myapplication2.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R

class ViewNotesActivity : AppCompatActivity() {
    private lateinit var listViewNotes: ListView
    private lateinit var buttonClearNotes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
        supportActionBar?.title = "Tela 3 - Ver Notas"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notes)

        listViewNotes = findViewById(R.id.listViewNotes)
        buttonClearNotes = findViewById(R.id.buttonClearNotes)

        loadNotesIntoListView()

        buttonClearNotes.setOnClickListener {
            val sharedPreferences = getSharedPreferences("NotesApp", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("notes")
            editor.remove("favorites")
            editor.apply()

            Toast.makeText(this, "Notas apagadas com sucesso!", Toast.LENGTH_SHORT).show()
            loadNotesIntoListView()
        }
    }

    private fun loadNotesIntoListView() {
        val notes = loadNotesFromSharedPreferences()
        val adapter = NoteAdapter(this, notes)
        listViewNotes.adapter = adapter
    }

    private fun loadNotesFromSharedPreferences(): List<String> {
        val sharedPreferences = getSharedPreferences("NotesApp", Context.MODE_PRIVATE)
        val notesSet = sharedPreferences.getStringSet("notes", mutableSetOf()) ?: setOf()
        return if (notesSet.isEmpty()) listOf("Nenhuma nota disponível") else notesSet.toList()
    }
}