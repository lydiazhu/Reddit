package com.example.reddit

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditGridPresenter(private val redditApi : RedditApi, private val view: RedditGridContract.View, private val redditItemRepository: RedditItemRepository) {

    private var itemList = ArrayList<RedditItem>()

    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    fun getRedditItems() {
        mCompositeDisposable?.add(
            redditApi.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }

    private fun handleResponse(result: RedditNewsResponse) {
        val redditItems = result.data.children.map {
            val item = it.data
            RedditItem(item.thumbnail, item.title, item.author)
        }

        itemList = ArrayList(redditItems)

        view.setupGridLayout(itemList)

        view.hideProgressBar()
    }

    private fun handleError(error: Throwable) {
        view.showErrorDialog()
        view.hideProgressBar()
    }
}