package com.example.reddit

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.findViewById(R.id.grid_item_title)
    val authorTextView: TextView = view.findViewById(R.id.grid_item_author)
}