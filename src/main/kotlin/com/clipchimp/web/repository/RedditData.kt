package com.clipchimp.web.repository

import org.json.JSONObject

class RedditData(json: String):JSONObject(json) {
    var jsonData = json

    var dataObj: JSONObject = this.optJSONObject("data")

    val title = dataObj.optString("title")
    val author_fullname = dataObj.optString("author_fullname")
    val upvote_ratio = dataObj.optInt("upvote_ratio")
    val upvotes = dataObj.optInt("ups")
    val twitch_url = dataObj.optString("url_overridden_by_dest")
}