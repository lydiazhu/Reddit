package com.example.reddit

import android.os.Bundle
import android.widget.GridLayout
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RedditGridActivity : AppCompatActivity() {

    @Inject
    lateinit var redditApi : RedditApi

    private var itemList = ArrayList<RedditItem>()

    private var mCompositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_grid_view)

        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(
            redditApi.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }

    private fun handleResponse(result: RedditNewsResponse) {

        val gridview = findViewById<RecyclerView>(R.id.grid_view)

        val news = result.data.children.map {
            val item = it.data
            RedditItem(item.thumbnail, item.title, item.author)
        }

        itemList = ArrayList(news)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridview.layoutManager = gridLayoutManager
        val adapter = RedditAdapter(itemList)

        gridview.adapter = adapter
    }

    private fun handleError(error: Throwable) {

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
