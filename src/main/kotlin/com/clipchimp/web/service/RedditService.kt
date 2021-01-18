package com.clipchimp.web.service

import com.clipchimp.web.redditConstruct.RedditAuthClient
import khttp.responses.Response;
import org.json.JSONObject
import khttp.get as httpGet;

class RedditService {
    private var subRedditJsonText: String = "";

    fun authenticateApplication(): Response {
        var redditAuth = RedditAuthClient()
        var authUrl: String = "https://www.reddit.com/api/v1/authorize?client_id=${redditAuth.clientId}" +
                "&response_type=${redditAuth.responseType}&state=${redditAuth.randomString}" +
                "&redirect_uri=${redditAuth.redirectUrl}&duration=${redditAuth.duration}&scope=${redditAuth.scope}";

        var result: Response = httpGet(authUrl);

        return result;
    }

    fun getSubredditJson(subRedditName: String): String {
        var redditSubJsonHot = "https://www.reddit.com/r/$subRedditName/hot.json"
        var header = HashMap<String, String>()
        header["Authorization"] = RedditAuthClient().getOauthToken()

        var response = khttp.get(redditSubJsonHot, headers = header)
        subRedditJsonText = response.text

        return response.text
    }

    fun getSubredditTopNPosts(subRedditName: String, topN: Int): String {
        if (subRedditJsonText.isEmpty()) {
            subRedditJsonText = getSubredditJson(subRedditName)
        }

        var jsonObject: JSONObject = JSONObject(subRedditJsonText)

        //jsonObject.getJSONObject("data").getJSONArray("children")//["data"]["children"]["data"][0].toString()
        return "NotYetImplemented"


    }

}