package com.example.myapplication2.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R

class AddNoteActivity : AppCompatActivity() {
    private lateinit var editTextTitle: EditText
    private lateinit var editTextNote: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
        supportActionBar?.title = "Tela 2 - Adicionar Notas"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextNote = findViewById(R.id.editTextNote)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val note = editTextNote.text.toString()

            saveNoteToSharedPreferences(title, note)
            finish() // Fechar a atividade após salvar a nota
        }
    }

    private fun saveNoteToSharedPreferences(title: String, note: String) {
        val sharedPreferences = getSharedPreferences("NotesApp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Carregar notas existentes, se houver
        val notesSet = sharedPreferences.getStringSet("notes", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        // Criar uma string representando a nota no formato "título:descrição"
        notesSet.add("$title:$note")

        // Salvar as notas de volta
        editor.putStringSet("notes", notesSet)
        editor.apply()
    }
}