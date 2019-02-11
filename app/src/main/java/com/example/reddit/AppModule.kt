package com.example.reddit

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val context: Context) {

    @Singleton
    @Provides
    fun providesUserDatabase(): RedditDatabase {
        return Room.
            databaseBuilder(context, RedditDatabase::class.java, RedditDatabase.TABLE_NAME_USER).
            allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun providesUserDao(redditDatabase: RedditDatabase) : UserDao {
        return redditDatabase.userDao()
    }
}
