package com.example.reddit.grid

import androidx.lifecycle.ViewModelProvider
import com.example.reddit.RedditApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GridViewModelModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(redditApi : RedditApi,
                                girdView : RedditGridView
    ): ViewModelProvider.Factory {
        return GridViewModelFactory(redditApi, girdView)
    }
}