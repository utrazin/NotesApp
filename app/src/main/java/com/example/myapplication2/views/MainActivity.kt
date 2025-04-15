package com.example.myapplication2.views

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
        supportActionBar?.title = "Tela 1 - Home Page"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonAddNote: Button = findViewById(R.id.buttonAddNote)
        val buttonViewNotes: Button = findViewById(R.id.buttonViewNotes)

        buttonAddNote.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        buttonViewNotes.setOnClickListener {
            val intent = Intent(this, ViewNotesActivity::class.java)
            startActivity(intent)
        }
    }
}