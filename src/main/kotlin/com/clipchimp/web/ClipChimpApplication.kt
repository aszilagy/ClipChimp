package com.clipchimp.web

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication as SpringBootApplication1

@SpringBootApplication1
class ClipChimpApplication

fun main(args: Array<String>) {
	runApplication<ClipChimpApplication>(*args)
}
