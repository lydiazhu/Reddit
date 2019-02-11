package com.example.reddit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val redditApi: RedditApi, private val view: RedditGridContract.View)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedditViewModel::class.java)) {
            return RedditViewModel(redditApi, view) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
