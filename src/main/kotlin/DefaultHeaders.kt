package com.example

import io.ktor.http.HttpHeaders
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import kotlin.time.Duration.Companion.days

//this plugin will attach a custom header to every response
//from our server

//this responses will be cached on our [ Android Application ]

fun Application.configureDefaultHeader() {

    //determines how long the response will be cached by
    //the client [ Android App ]
    install(DefaultHeaders) {
        val oneYearInSeconds = 365.days.inWholeSeconds
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=$oneYearInSeconds, immutable"
        )

    }
}
