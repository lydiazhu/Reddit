package com.example.reddit

class RedditDetailResponse(val data: RedditDetailDataResponse)

class RedditDetailDataResponse(val children: List<RedditDetailChildrenResponse>)

class RedditDetailChildrenResponse(val data: RedditDetailChildrenDataResponse)

class RedditDetailChildrenDataResponse (
    val preview : RedditPreviewResponse,
    val author : String,
    val title : String,
    val body : String
)

class RedditPreviewResponse(val images : List<RedditImageResponse>)

class RedditImageResponse(val source : RedditSourceResponse)

class RedditSourceResponse(val url: String)