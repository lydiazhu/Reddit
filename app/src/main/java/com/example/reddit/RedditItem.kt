package com.example.reddit

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
    val children: List<RedditChildrenResponse>
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
    val author: String,
    val title: String,
    val thumbnail: String
)
//
//class RedditDataResponse {
//    val children : List<RedditDataItems>
//}
//
//class RedditDataItems(val data: RedditItem)
//
class RedditItem (val thumbnail : String,
                  val title : String,
                  val author : String)