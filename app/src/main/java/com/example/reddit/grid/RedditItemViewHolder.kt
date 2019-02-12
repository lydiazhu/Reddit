package com.example.reddit.grid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.R

class RedditItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView = view.findViewById(R.id.grid_item_title)
    val authorTextView: TextView = view.findViewById(R.id.grid_item_author)
    val image : ImageView = view.findViewById(R.id.grid_item_view_image)
}