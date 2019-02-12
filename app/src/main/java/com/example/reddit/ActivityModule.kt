package com.example.reddit

import com.example.reddit.detail.DetailViewModelModule
import com.example.reddit.detail.RedditDetailViewModule
import com.example.reddit.detail.RedditDetailedActivity
import com.example.reddit.grid.GridViewModelModule
import com.example.reddit.grid.RedditGridActivity
import com.example.reddit.grid.RedditGridViewModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [RedditGridViewModule::class, GridViewModelModule::class])
    abstract fun bindRedditGridActivity() : RedditGridActivity

    @ContributesAndroidInjector(modules = [RedditDetailViewModule::class, DetailViewModelModule::class])
    abstract fun bindRedditDetailActivity() : RedditDetailedActivity
}
