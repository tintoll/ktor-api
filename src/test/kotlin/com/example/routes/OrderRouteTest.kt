package com.example.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderRouteTest {

    @Test
    fun testGetOrder() = testApplication {
        val response: HttpResponse = client.get("/order/2020-04-06-01")
        assertEquals(
            """{"number":"2020-04-06-01","contents":[{"item":"Ham Sandwich","amount":2,"price":5.5},{"item":"Water","amount":1,"price":1.5},{"item":"Beer","amount":3,"price":2.3},{"item":"Cheesecake","amount":1,"price":3.75}]}""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }
}