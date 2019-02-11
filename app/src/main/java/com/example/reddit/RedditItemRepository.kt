package com.example.reddit

import android.os.AsyncTask
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditItemRepository @Inject constructor(private val redditItemDao: RedditItemDao) {

    fun fetchAllReddits() : List<RedditItem> {
        return redditItemDao.fetchAllRedditItem()
    }

    fun insert(redditItem: RedditItem) {
        insertAsyncTask(redditItemDao).execute(redditItem)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: RedditItemDao) : AsyncTask<RedditItem, Void, Void>() {

        override fun doInBackground(vararg params: RedditItem): Void? {
            mAsyncTaskDao.insertRedditItem(params[0])
            return null
        }
    }
}