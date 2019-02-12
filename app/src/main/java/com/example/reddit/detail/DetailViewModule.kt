package com.example.reddit.detail

import androidx.lifecycle.ViewModelProvider
import com.example.reddit.Permalink
import com.example.reddit.RedditApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(redditApi : RedditApi,
                                detailView : RedditDetailView,
                                permalink: Permalink
    ): ViewModelProvider.Factory {
        return DetailViewModelFactory(redditApi, detailView, permalink)
    }
}