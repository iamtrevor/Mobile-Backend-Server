package com.example

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImpl

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.assertEquals


class ServerTest {

    private val heroRepository : HeroRepository by inject(HeroRepository::class.java)


    @Test
    fun accessRootEndpoint_AssertCorrectInformation() = testApplication {
        // loads default configuration
        application {
            module()
        }

        val response = client.get("/")
        // verify server root returns 200

        //both assertEquals() must be correct
        //for the test to succeed
        assertEquals(expected = HttpStatusCode.OK, actual = response.status)
        assertEquals(expected = "Welcome to our API", actual = response.bodyAsText())
    }



    @Test
    fun accessAllHeroesEndpoint_AssertNoInformation() = testApplication {
        application {
            module()
        }
        val response = client.get("/boruto/heroes")
        assertEquals(expected = HttpStatusCode.OK, actual = response.status)
        //result to be expected from server
        val expected = ApiResponse(
            success = true,
            message = "ok",
            prevPage = null,
            nextPage = 2,
            heroes = HeroRepositoryImpl().page1
        )
        //actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        assertEquals(
            expected = expected,
            actual = actual
        )
    }

    @Test
    fun ` test access for the second page`() = testApplication {
        application{
            module()
        }
        val response = client.get("/boruto/heroes?page=2")
        assertEquals(expected = HttpStatusCode.OK, actual = response.status)

        //expected response from server
        val expected = ApiResponse(
            success = true,
            message = "ok",
            prevPage = 1,
            nextPage = 3,
            heroes = HeroRepositoryImpl().page2
        )
        //actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())


        assertEquals(
            expected = expected,
            actual = actual
        )

    }


    @Test
    fun ` test access for the third page`() = testApplication {
        application{
            module()
        }

        val response = client.get("/boruto/heroes?page=3")

        assertEquals(expected = HttpStatusCode.OK, actual = response.status)

        //expected response from server
        val expected = ApiResponse(
            success = true,
            message = "ok",
            prevPage = 2,
            nextPage = 4,
            heroes = HeroRepositoryImpl().page3
        )

        //actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        assertEquals(
            expected = expected,
            actual = actual
        )
    }


    @Test
    fun ` test access for the fourth page`() = testApplication {
        application{
            module()
        }
        val response = client.get("/boruto/heroes?page=4")
        assertEquals(expected = HttpStatusCode.OK, actual = response.status)

        //expected response from server
        val expected = ApiResponse(
            success = true,
            message = "ok",
            prevPage = 3,
            nextPage = 5,
            heroes = HeroRepositoryImpl().page4
        )

        //actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())

        assertEquals(
            expected = expected,
            actual = actual
        )

    }





}









