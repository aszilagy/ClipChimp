package com.clipchimp.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RedditController {
    @GetMapping("/api/reddit")
    fun redditWelcome(): String{
        return "Welcome to Reddit";
    }

}