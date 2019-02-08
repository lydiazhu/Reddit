package com.example.twitter

import android.os.Bundle
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_twitter_grid_activty.*

class TwitterGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_grid_activty)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
