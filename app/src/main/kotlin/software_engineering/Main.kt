package software_engineering

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 80) {
        routing {
            get("/") {
                call.respondRedirect("/display")
            }
        }
    }.start(wait = true)
}