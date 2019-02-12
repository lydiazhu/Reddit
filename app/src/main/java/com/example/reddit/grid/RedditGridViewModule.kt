package com.example.reddit.grid

import dagger.Binds
import dagger.Module


@Module
abstract class RedditGridViewModule {

    @Binds
    internal abstract fun provideRedditGridView(redditGridViewActivity: RedditGridActivity): RedditGridView
}