package com.example.reddit.detail

class RedditDetailResponse(val data: RedditDetailDataResponse)

class RedditDetailDataResponse(val children: List<RedditDetailChildrenResponse>)

class RedditDetailChildrenResponse(val data: RedditDetailChildrenDataResponse)

class RedditDetailChildrenDataResponse (
    val preview : RedditPreviewResponse =
        RedditPreviewResponse(listOf(RedditImageResponse(RedditSourceResponse("No Image")))),
    val author : String,
    val title : String,
    val body : String
)

class RedditPreviewResponse(var images : List<RedditImageResponse>)

class RedditImageResponse(val source : RedditSourceResponse)

class RedditSourceResponse(var url : String) {
}