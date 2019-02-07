package com.example.twitter

import android.arch.persistence.room.Entity

@Entity(tableName = UserDatabase.TABLE_NAME_USER)
class User (name : String) {
}