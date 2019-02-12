package com.example.reddit.grid

import com.example.reddit.RedditItem

interface RedditGridView {
    fun setupGridLayout(redditItems: ArrayList<RedditItem>)
    fun hideProgressBar()
    fun showErrorDialog()
}