package com.example.reddit

import androidx.room.*
import javax.inject.Singleton


@Singleton
@Dao
interface RedditItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRedditItem(redditItem: RedditItem)

    @Query("DELETE FROM reddit_item_table")
    fun deleteRedditItems()

    @Query("SELECT * from reddit_item_table")
    fun fetchAllRedditItem(): List<RedditItem>
}
