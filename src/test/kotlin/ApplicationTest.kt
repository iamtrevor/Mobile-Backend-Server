package com.example

import com.example.models.ApiResponse
import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImpl
import com.example.repository.NEXT_PAGE_KEY
import com.example.repository.PREVIOUS_PAGE_KEY
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals


class ServerTest {

    //private val heroRepository : HeroRepository by inject(HeroRepository::class.java)


    @Test
    fun accessAllHeroPages() = testApplication {
        application {
            module()
        }
        val repository = HeroRepositoryImpl()
        val heroPages = listOf(
            repository.page1,
            repository.page2,
            repository.page3,
            repository.page4,
            repository.page5
        )

        for (page in 1..5) {
            val response = client.get("/boruto/heroes?page=$page")

            assertEquals(HttpStatusCode.OK, response.status)

            val actual = Json.decodeFromString<ApiResponse>(
                response.bodyAsText()
            )

            val expected = ApiResponse(
                success = true,
                message = "ok",
                prevPage = calculatePage(page = page)["prevPage"],
                nextPage = calculatePage(page = page)["nextPage"],
                heroes = heroPages[page - 1]
            )
            assertEquals(expected, actual)
        }
    }



    private fun calculatePage(page : Int) : Map<String, Int?>{
        var prevPage : Int? = page
        var nextPage : Int? = page

        if(page in 1..4){
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5){
            prevPage = prevPage?.minus(1)
        }
        if(page == 1){
            prevPage = null
        }
        if(page == 5){
            nextPage = null
        }

        return mapOf(PREVIOUS_PAGE_KEY to prevPage, NEXT_PAGE_KEY to nextPage)
    }



    @Test
    fun `access all heroes endpoint, query non-existing page number, assert error`() = testApplication{
        val heroRepository = HeroRepositoryImpl()
        application {
            module()
        }
        //expected response from server
        val expected = ApiResponse(
            success = false,
            message = "Heroes not found"
        )
        val response = client.get("/boruto/heroes?page=6")
        // actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
        assertEquals(expected = HttpStatusCode.NotFound, actual = response.status)
        assertEquals(expected, actual)
    }


    
    @Test
    fun `access all heroes endpoint, query invalid page number, assert error`() = testApplication{
        val heroRepository = HeroRepositoryImpl()
        application {
            module()
        }
        //expected response from server
        val expected = ApiResponse(
            success = false,
            message = "Only Numbers allowed"
        )
        val response = client.get("/boruto/heroes?page=invalid")
        // actual response from server
        val actual = Json.decodeFromString<ApiResponse>(response.bodyAsText())
        assertEquals(expected = HttpStatusCode.BadRequest, actual = response.status)
        assertEquals(expected = expected, actual = actual)
    }
}








