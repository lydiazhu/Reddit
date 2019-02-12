package com.example.reddit.grid

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
import com.example.reddit.LoginActivity
import com.example.reddit.Permalink
import com.example.reddit.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit_grid_view.*
import javax.inject.Inject

class RedditGridActivity : AppCompatActivity(), RedditGridView {

    @Inject
    lateinit var viewModelFactory: GridViewModelFactory

    @Inject
    lateinit var permalink: Permalink

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_grid_view)

        setSupportActionBar(grid_view_toolbar)

        val sharedPref = getSharedPreferences(LoginActivity::class.java.name, Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString(getString(R.string.user_name), null)
        supportActionBar!!.title = getString(R.string.app_project_title)

        progressBar = findViewById(R.id.progressBar)
        ViewModelProviders.of(this, viewModelFactory).get(RedditGridViewModel::class.java)
            .getLiveRedditItems().observe(this,
                Observer<List<RedditChildrenResponse>> { t ->
                    val items = t.map {
                        val item = it.data
                        RedditItem(item.thumbnail, item.title, item.author, item.permalink)
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
        val adapter = RedditGridAdapter(redditItems, permalink)
        gridview.adapter = adapter
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(getString(R.string.error_message))
        // Create the AlertDialog object and return it
        builder.create()
        builder.show()
    }
}
