package com.example.reddit.grid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.RedditApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditGridViewModel (redditApi: RedditApi, private val view: RedditGridView) : ViewModel() {

    private var liveRedditItems : MutableLiveData<List<RedditChildrenResponse>>
            = MutableLiveData()

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

    private fun handleResponse(result: RedditPostsResponse) {
        liveRedditItems.value = result.data.children
        view.setupGridLayout(result.data.children)
    }

    private fun handleError(error: Throwable) {
        view.showErrorDialog()
        view.hideProgressBar()
    }

    override fun onCleared() {
        mCompositeDisposable!!.clear()
    }
}