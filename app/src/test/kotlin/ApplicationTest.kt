package software_engineering

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ApplicationTest {
    @Test
    fun testNotFound() = testApplication {
        application {
            configureRouting()
        }
        val response = client.get("/display_obj")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun testOK() = testApplication {
        application {
            configureRouting()
        }
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testNoMultipartHeader() = testApplication {
        application {
            configureRouting()
        }
        val exception: Throwable = assertThrows { client.post("/display") }
        assertEquals("Content-Type header is required for multipart processing", exception.message)
    }
}