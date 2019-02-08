package com.example.reddit

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector()
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}
