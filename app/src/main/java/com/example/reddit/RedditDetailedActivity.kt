package com.example.reddit

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reddit_detailed.*
import javax.inject.Inject

class RedditDetailedActivity : AppCompatActivity() {

    @Inject
    lateinit var redditApi: RedditApi

    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var bodyTextView: TextView


    private var mCompositeDisposable: CompositeDisposable? = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_detailed)
        setSupportActionBar(reddit_detail_toolbar)
        val permalink = intent.getStringExtra("Permalink ")

        val sharedPref = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString("UserName", "UserName")
        supportActionBar!!.title = "Reddit Client"

        titleTextView = findViewById<TextView>(R.id.reddit_detail_title_text)
        authorTextView = findViewById<TextView>(R.id.reddit_detail_author_text)
        imageView = findViewById<ImageView>(R.id.reddit_detail_image_view)
        bodyTextView = findViewById<TextView>(R.id.reddit_detail_body_text)

        mCompositeDisposable?.add(
            redditApi.getDetailedPost(permalink.substring(1, permalink.length - 1) + ".json")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError)
        )
    }

    private fun handleResponse(result: List<RedditDetailResponse>) {
        val it = result[0].data.children[0].data

        titleTextView.text = it.title
        authorTextView.text = it.author
        Glide.with(this).load(it.preview.images[0].source.url.replace("amp;", ""))
            .into(imageView)
        bodyTextView.text = it.body

    }

    private fun handleError(error: Throwable) {
        error.localizedMessage
    }
}
