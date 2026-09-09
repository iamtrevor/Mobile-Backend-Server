package com.example

import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.get
import io.ktor.server.routing.routing



fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}






