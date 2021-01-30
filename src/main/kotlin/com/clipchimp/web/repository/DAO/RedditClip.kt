package com.clipchimp.web.repository.DAO

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class RedditClip(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    var title: String = "",
    var authorName: String = "",
    var twitchUrl: String = ""
)