package com.example.reddit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RedditAdapter (private var redditList: ArrayList<RedditItem>) : RecyclerView.Adapter<RedditItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {

        return RedditItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false))
    }

    override fun getItemCount(): Int {
        return redditList.size
    }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        holder?.titleTextView.text =  redditList[position].title
        holder?.authorTextView.text = redditList[position].author
    }
}