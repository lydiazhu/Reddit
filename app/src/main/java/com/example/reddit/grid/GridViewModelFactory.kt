package com.example.reddit.grid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.reddit.*
import com.example.reddit.grid.RedditGridView
import com.example.reddit.grid.RedditGridViewModel
import javax.inject.Inject

class GridViewModelFactory @Inject constructor(private val redditApi: RedditApi,
                                               private val gridView: RedditGridView)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RedditGridViewModel::class.java)) {
            return RedditGridViewModel(redditApi, gridView) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}
