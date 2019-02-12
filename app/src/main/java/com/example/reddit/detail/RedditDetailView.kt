package com.example.reddit.detail

interface RedditDetailView {
    fun setupView(result : List<RedditDetailResponse>)
    fun hideProgressBar()
    fun showErrorDialog()
}