package com.example.reddit.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.Permalink
import com.example.reddit.RedditApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditDetailViewModel(redditApi: RedditApi, private val view : RedditDetailView, val permalink: Permalink) : ViewModel() {

        private var liveRedditItems : MutableLiveData<List<RedditDetailResponse>> = MutableLiveData()

        private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

        fun getRedditDetailItem(): MutableLiveData<List<RedditDetailResponse>> {
            return liveRedditItems
        }

        init {
            mCompositeDisposable?.add(
                redditApi.getDetailedPost(permalink.getLink())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponse, this::handleError))
        }


        private fun handleResponse(result: List<RedditDetailResponse>) {
            liveRedditItems.value = result
            view.setupView(result)
        }

        private fun handleError(error: Throwable) {
            view.showErrorDialog()
        }

        override fun onCleared() {
            mCompositeDisposable!!.clear()
        }
}