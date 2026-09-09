package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import javax.naming.AuthenticationException

fun Application.configureStatusPages() {
    install(StatusPages){
        exception<AuthenticationException> { call, _ ->
            call.respond(
                message = "We caught an exception",
                status = HttpStatusCode.OK
            )
        }
        
    }
}
