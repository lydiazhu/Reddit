package com.example.reddit

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions




class RedditAdapter (private var redditList: ArrayList<RedditItem>) : RecyclerView.Adapter<RedditItemViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {
        this.context = parent.context
        return RedditItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false))
    }

    override fun getItemCount(): Int {
        return redditList.size
    }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        holder?.titleTextView.text =  redditList[position].title
        holder?.authorTextView.text = redditList[position].author
        if (!redditList[position].thumbnail.contains("http")) {
            holder?.image.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background))
        } else {
            Glide.with(context).load(redditList[position].thumbnail)
                .apply(RequestOptions().override(600, 600))
                .into(holder?.image)
        }
    }
}