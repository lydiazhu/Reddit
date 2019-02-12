package com.example.reddit

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit_detailed.*
import kotlinx.android.synthetic.main.activity_reddit_grid_view.*
import javax.inject.Inject

class RedditGridActivity : AppCompatActivity(), RedditGridContract.View {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_grid_view)

        setSupportActionBar(grid_view_toolbar)

        val sharedPref = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString("UserName", "UserName")
        supportActionBar!!.title = "Reddit Client"

        progressBar = findViewById(R.id.progressBar)
        ViewModelProviders.of(this, viewModelFactory).get(RedditViewModel::class.java)
            .getLiveRedditItems().observe(this,
                Observer<List<RedditChildrenResponse>> { t ->
                    val items = t.map {
                        val item = it.data
                        RedditItem(item.thumbnail, item.title, item.author)
                    }
                    setupGridLayout(ArrayList(items))
                    hideProgressBar()

                })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun setupGridLayout(redditItems: ArrayList<RedditItem>) {
        val gridview = findViewById<RecyclerView>(R.id.grid_view)
        val gridLayoutManager = GridLayoutManager(this, 2)
        gridview.layoutManager = gridLayoutManager
        val adapter = RedditAdapter(redditItems)
        gridview.adapter = adapter
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Sorry. We are having trouble loading data.")
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
    }
}
