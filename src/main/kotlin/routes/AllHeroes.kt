package com.example.routes

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject


fun Route.getAllHeroes() {

    //injecting heroRepository implementation
    val heroRepository : HeroRepository by inject()

    get("/boruto/heroes"){
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1

            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page = page)

            call.respond(message = apiResponse, status = HttpStatusCode.OK)

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
                status = HttpStatusCode.NotFound
            )
        }
    }
}