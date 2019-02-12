package com.example.reddit

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesPermalink() : Permalink {
        return Permalink()
    }
}
