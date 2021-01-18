package com.clipchimp.web.controller

import com.clipchimp.web.redditConstruct.RedditAuthClient
import com.clipchimp.web.service.RedditService
import khttp.responses.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/reddit")
class RedditController {
    val redditService = RedditService()

    @GetMapping("/healthcheck")
    fun healthcheck(): String {
        return "Health is ok"
    }

    /* Disabled until Auth reset is required
    @GetMapping("/auth")
    fun requestAuth(): String{
        var result: Response = RedditService().authenticateApplication()
        return result.text
    }
    */

    @GetMapping("/user")
    fun getUser(@RequestParam(value = "id") userId: String): String {
        var redditUserApi = "https://www.reddit.com/api/multi/user/$userId"
        var header = HashMap<String, String>()
        header["Authorization"] = RedditAuthClient().getOauthToken()

        var response = khttp.get(redditUserApi, headers = header)

        return response.text
    }

    @GetMapping("/subreddit")
    fun getSubredditHot(@RequestParam(value = "name") subRedditName: String): String {
        return redditService.getSubredditJson(subRedditName)
    }

    @GetMapping("/subreddit/getTopN")
    fun getSubredditHotTopN(@RequestParam(value = "subRedditName") subRedditName: String,
                            @RequestParam(value = "topN") topN: Int): String {
        return redditService.getSubredditTopNPosts(subRedditName, topN)
    }
}