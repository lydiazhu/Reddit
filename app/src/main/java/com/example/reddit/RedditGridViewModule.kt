package com.example.reddit

import dagger.Binds
import dagger.Module


@Module
abstract class RedditGridViewModule {

    @Binds
    internal abstract fun provideRedditGridView(redditGridViewActivity: RedditGridActivity): RedditGridContract.View
}