package com.example.reddit

interface RedditGridContract {
    interface View {
        fun setupGridLayout(redditItems : ArrayList<RedditItem>)
        fun hideProgressBar()
        fun showErrorDialog()
    }
}