package com.example.myapplication2.views

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication2.R

class NoteAdapter(
    private val context: Context,
    private var notes: List<String>
) : BaseAdapter() {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("NotesApp", Context.MODE_PRIVATE)
    private val favoriteNotes: MutableSet<String> = sharedPreferences.getStringSet("favorites", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

    override fun getCount(): Int = notes.size
    override fun getItem(position: Int): String = notes[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        val textViewTitle = itemView.findViewById<TextView>(R.id.textViewTitle)
        val textViewBody = itemView.findViewById<TextView>(R.id.textViewBody)
        val imageViewFavorite = itemView.findViewById<ImageView>(R.id.imageViewFavorite)

        val note = notes[position]

        // Divide título e texto
        val parts = note.split(":", limit = 2)
        val title = parts.getOrNull(0) ?: ""
        val body = parts.getOrNull(1) ?: ""

        textViewTitle.text = title
        textViewBody.text = body

        val isFavorite = favoriteNotes.contains(note)
        imageViewFavorite.setImageResource(
            if (isFavorite) android.R.drawable.btn_star_big_on
            else android.R.drawable.btn_star_big_off
        )

        imageViewFavorite.setOnClickListener {
            if (isFavorite) {
                favoriteNotes.remove(note)
            } else {
                favoriteNotes.add(note)
            }
            sharedPreferences.edit().putStringSet("favorites", favoriteNotes).apply()
            notifyDataSetChanged()
        }

        return itemView
    }
}