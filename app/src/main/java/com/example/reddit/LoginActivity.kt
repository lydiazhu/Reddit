package com.example.reddit

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.reddit.grid.RedditGridActivity
import dagger.android.AndroidInjection

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val button = findViewById<Button>(R.id.activity_login_login_button)
        val editText = findViewById<EditText>(R.id.activity_login_user_name)
        button.setOnClickListener {
            val sharedPref = getSharedPreferences(LoginActivity::class.java.name, Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString(getString(R.string.user_name), editText.text.toString())
                apply()
            }
            val intent = Intent(this, RedditGridActivity::class.java)
            intent.flags = FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
