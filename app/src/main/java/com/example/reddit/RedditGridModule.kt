package com.example.reddit

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RedditGridModule {

    @Provides
    @Singleton
    internal fun getViewModelFactory(redditApi : RedditApi,
        gridView: RedditGridContract.View): ViewModelProvider.Factory {
        return ViewModelFactory(redditApi, gridView)
    }

}