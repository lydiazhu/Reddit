package com.example.reddit

import android.content.Context
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_reddit_detailed.*

class RedditDetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reddit_detailed)
        setSupportActionBar(reddit_detail_toolbar)

        val sharedPref = getSharedPreferences("LoginActivity", Context.MODE_PRIVATE)
        supportActionBar!!.subtitle = sharedPref.getString("UserName", "UserName")
        supportActionBar!!.title = "Reddit Client"
    }

}
