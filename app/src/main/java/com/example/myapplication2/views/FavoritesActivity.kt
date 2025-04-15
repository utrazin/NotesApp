package com.example.myapplication2.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication2.R

class FavoritesActivity : AppCompatActivity() {
    private lateinit var listViewFavorites: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#1976D2")))
        supportActionBar?.title = "Tela 4 - Notas Favoritas"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        listViewFavorites = findViewById(R.id.listViewFavorites)

        val sharedPreferences = getSharedPreferences("NotesApp", Context.MODE_PRIVATE)
        val favoriteSet = sharedPreferences.getStringSet("favorites", setOf()) ?: setOf()
        val favorites = favoriteSet.toList()

        val adapter = NoteAdapter(this, favorites)
        listViewFavorites.adapter = adapter
    }
}