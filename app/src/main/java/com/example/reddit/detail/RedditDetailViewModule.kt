package com.example.reddit.detail

import dagger.Binds
import dagger.Module

@Module
abstract class RedditDetailViewModule {

    @Binds
    abstract fun provideRedditDetailView(redditDetailedActivity : RedditDetailedActivity): RedditDetailView
}