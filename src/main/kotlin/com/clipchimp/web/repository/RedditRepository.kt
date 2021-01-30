package com.clipchimp.web.repository

import com.clipchimp.web.repository.DAO.RedditClip
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RedditRepository : CrudRepository<RedditClip, Long> {
    fun findByTwitchUrl(url: String): RedditClip?
}