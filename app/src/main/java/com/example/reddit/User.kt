package com.example.reddit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RedditDatabase.TABLE_NAME_USER)
class User (@PrimaryKey @ColumnInfo(name = "userName") val userName: String)