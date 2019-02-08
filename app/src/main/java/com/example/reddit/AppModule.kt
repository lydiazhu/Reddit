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
    fun providesUserDatabase(): UserDatabase {
        return Room.
            databaseBuilder(context, UserDatabase::class.java, UserDatabase.TABLE_NAME_USER).
            allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun providesUserDao(userDatabase: UserDatabase) : UserDao {
        return userDatabase.userDao()
    }
}
