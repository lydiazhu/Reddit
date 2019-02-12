package com.example.reddit

import javax.inject.Singleton

@Singleton
class Permalink {
    private lateinit var link : String

    fun setLink(link : String) {
        this.link = link
    }

    fun getLink() : String {
        return link
    }
}