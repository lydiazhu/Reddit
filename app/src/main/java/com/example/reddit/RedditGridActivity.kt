package com.example.reddit

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RedditGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_grid_view)


        val itemList = ArrayList<RedditItem>()
        itemList.add(RedditItem("title1", "peter"))
        itemList.add(RedditItem("title2", "sally"))

        val gridview = findViewById<RecyclerView>(R.id.grid_view)

        val adapter = RedditAdapter(itemList)
        gridview.layoutManager = GridLayoutManager(this, 3)
        gridview.adapter = adapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
