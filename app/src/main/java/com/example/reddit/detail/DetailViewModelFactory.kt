package com.example.reddit.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reddit.*
import com.example.reddit.grid.RedditGridView
import com.example.reddit.grid.RedditGridViewModel
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val redditApi: RedditApi,
                                           private val detailView : RedditDetailView,
                                           private val permalink: Permalink
)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedditDetailViewModel::class.java)) {
            return RedditDetailViewModel(redditApi, detailView, permalink) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
