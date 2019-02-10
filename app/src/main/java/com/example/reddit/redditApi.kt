package com.example.reddit

import io.reactivex.Observable
import retrofit2.http.GET

interface RedditApi {
    @GET("/r/all.json")
    fun getAllPosts() : Observable<RedditNewsResponse>
}