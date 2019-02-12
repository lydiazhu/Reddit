package com.example.reddit.grid

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.reddit.Permalink
import com.example.reddit.R
import com.example.reddit.detail.RedditDetailedActivity


class RedditGridAdapter (private var redditList: ArrayList<RedditItem>, val permalink: Permalink) : RecyclerView.Adapter<RedditItemViewHolder>() {
    lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RedditItemViewHolder {
        this.context = parent.context
        return RedditItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.grid_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return redditList.size
    }

    override fun onBindViewHolder(holder: RedditItemViewHolder, position: Int) {
        holder.titleTextView.text =  redditList[position].title
        holder.authorTextView.text = redditList[position].author
        if (!redditList[position].thumbnail.contains("http")) {
            holder.image.setImageDrawable(context.getDrawable(R.drawable.ic_launcher_background))
        } else {
            Glide.with(context).load(redditList[position].thumbnail)
                .apply(RequestOptions().override(600, 600))
                .into(holder.image)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, RedditDetailedActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            val link = redditList[position].permalink
            permalink.setLink(redditList[position].permalink.substring(1, link.length-1)+".json")
            context.startActivity(intent)
        }
    }
}