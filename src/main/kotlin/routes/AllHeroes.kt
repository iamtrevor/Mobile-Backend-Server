package com.example.routes

import com.example.models.ApiResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get


fun Route.getAllHeroes() {

    get("/boruto/heroes"){
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)

            call.respond(message = page)

        } catch (e: NumberFormatException){
            call.respond(
                message = ApiResponse(success = false,
                    message = "Only Numbers allowed"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false,
                    message = "Heroes not found"),
                status = HttpStatusCode.BadRequest
            )
        }


    }
}