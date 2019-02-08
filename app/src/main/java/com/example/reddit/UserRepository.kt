package com.example.reddit

import android.os.AsyncTask
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserRepository @Inject constructor(private val userDao: UserDao) {

    fun fetchAllUsers() : List<User> {
        return userDao.fetchAllUsers()
    }

    fun insert(user: User) {
        insertAsyncTask(userDao).execute(user)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: UserDao) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg params: User): Void? {
            mAsyncTaskDao.insertUser(params[0])
            return null
        }
    }
}