package com.example.reddit.grid

interface RedditGridView {
    fun setupGridLayout(redditItems: List<RedditChildrenResponse>)
    fun hideProgressBar()
    fun showErrorDialog()
}