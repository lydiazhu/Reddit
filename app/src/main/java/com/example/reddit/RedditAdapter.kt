package com.example.reddit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions




class RedditAdapter (private var redditList: ArrayList<RedditItem>) : RecyclerView.Adapter<RedditItemViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {
        this.context = parent.context
        val holder = RedditItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false))
        holder.itemView.setOnClickListener {
            val intent = Intent(context, RedditDetailedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)

        }
        return holder
    }

    override fun getItemCount(): Int {
        return redditList.size
    }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        holder?.titleTextView.text =  "Title: " + redditList[position].title
        holder?.authorTextView.text = "Author: " + redditList[position].author
        if (!redditList[position].thumbnail.contains("http")) {
            holder?.image.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background))
        } else {
            Glide.with(context).load(redditList[position].thumbnail)
                .apply(RequestOptions().override(600, 600))
                .into(holder?.image)
        }
    }
}