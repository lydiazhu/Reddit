package com.example.reddit

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reddit_detailed.*
import javax.inject.Inject

class RedditGridActivity : AppCompatActivity() {

    @Inject
    lateinit var redditApi : RedditApi

    private var itemList = ArrayList<RedditItem>()

    private var mCompositeDisposable: CompositeDisposable? = null
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_grid_view)

        setSupportActionBar(grid_view_toolbar)
        val extras = intent.extras
        if (extras != null) {
            val username = extras.getString("UserName")
            supportActionBar!!.subtitle = username
            supportActionBar!!.title = "Reddit Client"
        }

        mCompositeDisposable = CompositeDisposable()

        progressBar = findViewById(R.id.progressBar)

        mCompositeDisposable?.add(
            redditApi.getAllPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString("UserName", "UserName")
        supportActionBar!!.title = "Reddit Client"
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
        progressBar.visibility = View.GONE

    }

    private fun handleError(error: Throwable) {

        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
