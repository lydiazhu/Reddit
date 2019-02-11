package com.example.reddit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
    val children: List<RedditChildrenResponse>
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
    val author: String,
    val title: String,
    val thumbnail: String
)

@Entity(tableName = RedditDatabase.TABLE_NAME_REDDIT_ITEM)
class RedditItem(
//    @PrimaryKey @ColumnInfo(name = "userName") val id : Int,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String
)