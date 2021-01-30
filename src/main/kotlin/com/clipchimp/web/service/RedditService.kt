package com.clipchimp.web.service

import com.clipchimp.web.redditConstruct.RedditAuthClient
import com.clipchimp.web.repository.DAO.RedditClip
import com.clipchimp.web.repository.RedditData
import com.clipchimp.web.repository.RedditRepository
import khttp.responses.Response;
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jackson.JsonObjectDeserializer
import org.springframework.stereotype.Service
import khttp.get as httpGet;

@Service
class RedditService(private val redditRepository: RedditRepository) {
    private var subRedditJsonText: String = "";

    fun authenticateApplication(): Response {
        val redditAuth = RedditAuthClient()
        val authUrl: String = "https://www.reddit.com/api/v1/authorize?client_id=${redditAuth.clientId}" +
                "&response_type=${redditAuth.responseType}&state=${redditAuth.randomString}" +
                "&redirect_uri=${redditAuth.redirectUrl}&duration=${redditAuth.duration}&scope=${redditAuth.scope}";

        val result: Response = httpGet(authUrl);

        return result;
    }

    fun getSubredditJson(subRedditName: String): String {
        val redditSubJsonHot = "https://www.reddit.com/r/$subRedditName/hot.json"
        val header = HashMap<String, String>()
        header["Authorization"] = RedditAuthClient().getOauthToken()

        val response = khttp.get(redditSubJsonHot, headers = header)
        subRedditJsonText = response.text

        return response.text
    }

    /* 0th element in jsonDataObject is always auto-mod sticky
     *
     */
    fun getSubredditTopNPosts(subRedditName: String, topN: Int): List<RedditData> {
        if (subRedditJsonText.isEmpty()) {
            subRedditJsonText = getSubredditJson(subRedditName)
        }

        val jsonObject: JSONObject = JSONObject(subRedditJsonText)

        val jsonDataArray: JSONArray = jsonObject.getJSONObject("data").getJSONArray("children")

        val data: List<RedditData> = jsonDataArray
            .let { 0.until(topN).map { i -> it.optJSONObject(i) } }
            .map { RedditData(it.toString()) }


        for (d in data) {
            if (redditRepository.findByTwitchUrl(d.twitch_url) == null) {
                val redditClip = RedditClip(0, d.title, d.author_fullname, d.twitch_url)
                redditRepository.save(redditClip)
            }
        }

        return data
    }

}