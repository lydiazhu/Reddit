package com.example.reddit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reddit.detail.RedditDetailResponse
import com.example.reddit.detail.RedditDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RedditDetailViewModel(private val redditApi: RedditApi, val view : RedditDetailView, val permalink: Permalink) : ViewModel() {

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
            error
        }

        override fun onCleared() {
            mCompositeDisposable!!.clear()
        }
}