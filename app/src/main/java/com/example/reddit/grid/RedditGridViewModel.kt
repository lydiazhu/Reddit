package com.example.reddit.grid

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.RedditApi
import com.example.reddit.RedditChildrenResponse
import com.example.reddit.RedditItem
import com.example.reddit.RedditNewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditGridViewModel (
    redditApi: RedditApi,
    private val view: RedditGridView) : ViewModel() {

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
            RedditItem(item.thumbnail, item.title, item.author, item.permalink)
        }

        liveRedditItems.value = result.data.children

        view.setupGridLayout(ArrayList(redditItems))

        view.hideProgressBar()
    }

    private fun handleError(error: Throwable) {
        view.showErrorDialog()
        view.hideProgressBar()
    }

    override fun onCleared() {
        mCompositeDisposable!!.clear()
    }
}