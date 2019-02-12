package com.example.reddit

import com.example.reddit.detail.RedditDetailResponse
import com.example.reddit.grid.RedditPostsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url


interface RedditApi {
    @GET("/r/all.json")
    fun getAllPosts() : Observable<RedditPostsResponse>

    @GET
    fun getDetailedPost(@Url permalink : String): Observable<List<RedditDetailResponse>>
}