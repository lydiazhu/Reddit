package com.example.reddit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditViewModel (private val redditApi: RedditApi,
                       private val view: RedditGridContract.View) : ViewModel() {

    private var liveRedditItems : MutableLiveData<List<RedditChildrenResponse>> = MutableLiveData()

    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    init {
        mCompositeDisposable?.add(
            redditApi.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))
    }

    fun getLiveRedditItems(): MutableLiveData<List<RedditChildrenResponse>> {
        return liveRedditItems
    }

    private fun handleResponse(result: RedditNewsResponse) {
        val redditItems = result.data.children.map {
            val item = it.data
            RedditItem(item.thumbnail, item.title, item.author)
        }

        liveRedditItems.value = result.data.children

        view.setupGridLayout(ArrayList(redditItems))

        view.hideProgressBar()
    }

    private fun handleError(error: Throwable) {
        view.showErrorDialog()
        view.hideProgressBar()
    }

}