package com.clipchimp.web.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WebController {
    @GetMapping("/")
    fun welcome(): String{
        return "Welcome to ClipChimp";
    }
}