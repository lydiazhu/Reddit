package com.example.reddit

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


interface RedditApi {
    @GET("/r/all.json")
    fun getAllPosts() : Observable<RedditNewsResponse>

    @GET
    fun getDetailedPost(@Url permalink : String): Observable<List<RedditDetailResponse>>
}