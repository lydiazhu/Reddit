package com.example.reddit

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class RedditDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
//    abstract fun redditItemDao() : RedditItemDao

    companion object {
        const val TABLE_NAME_USER = "user_table"
        const val TABLE_NAME_REDDIT_ITEM = "reddit_item_table"
        }

}