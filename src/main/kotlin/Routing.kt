package com.example

import com.example.routes.getAllHeroes
import com.example.routes.root
import com.example.routes.searchHeroes
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javax.naming.AuthenticationException


fun Application.configureRouting() {
    routing {
        root()
        getAllHeroes()
        searchHeroes()

        get("/test2"){
            throw AuthenticationException()
        }

        static("/images"){
            resources("images")
        }

        get("{...}") {
            call.respondText(
                text = "Page not Found",
                status = HttpStatusCode.NotFound
            )
        }

    }
}

