package com.example.reddit.grid

class RedditPostsResponse(val data: RedditDataResponse)

class RedditDataResponse(val children: List<RedditChildrenResponse>)

class RedditChildrenResponse(val data: RedditPostsDataResponse)

class RedditPostsDataResponse(
    val author: String,
    val title: String,
    val thumbnail: String,
    val permalink : String
)

class RedditItem(
    val thumbnail : String,
    val title : String,
    val author : String,
    val permalink : String
)