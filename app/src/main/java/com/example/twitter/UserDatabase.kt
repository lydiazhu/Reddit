package com.example.twitter

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val TABLE_NAME_USER = "user_table"
    }
}