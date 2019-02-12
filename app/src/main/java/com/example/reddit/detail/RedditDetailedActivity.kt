package com.example.reddit.detail

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.reddit.R
import com.example.reddit.RedditDetailViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit_detailed.*
import javax.inject.Inject

class RedditDetailedActivity : AppCompatActivity(), RedditDetailView {

    @Inject
    lateinit var viewModelFactory: DetailViewModelFactory

    private lateinit var titleTextView: TextView
    private lateinit var authorTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var bodyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_detailed)
        setSupportActionBar(reddit_detail_toolbar)
        val sharedPref = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString("UserName", "UserName")
        supportActionBar!!.title = "Reddit Client"

        titleTextView = findViewById(R.id.reddit_detail_title_text)
        authorTextView = findViewById(R.id.reddit_detail_author_text)
        imageView = findViewById(R.id.reddit_detail_image_view)
        bodyTextView = findViewById(R.id.reddit_detail_body_text)


        ViewModelProviders.of(this, viewModelFactory).get(RedditDetailViewModel::class.java)
            .getRedditDetailItem().observe(this,
                Observer<List<RedditDetailResponse>> { t ->
                    setupView(t)
                })
    }

    override fun setupView(result : List<RedditDetailResponse>) {
        titleTextView.text = result[0].data.children[0].data.title
        authorTextView.text = result[0].data.children[0].data.author
        Glide.with(this).load(result[0].data.children[0].data.preview.images[0].source.url.replace("amp;", ""))
            .into(imageView)
        bodyTextView.text = result[1].data.children[0].data.body
    }
}