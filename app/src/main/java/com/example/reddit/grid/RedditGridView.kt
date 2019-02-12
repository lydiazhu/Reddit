package com.example.reddit.grid

interface RedditGridView {
    fun setupGridLayout(redditItems: ArrayList<RedditItem>)
    fun hideProgressBar()
    fun showErrorDialog()
}