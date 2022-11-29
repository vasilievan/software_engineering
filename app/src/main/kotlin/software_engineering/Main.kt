package software_engineering

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*

fun main(args: Array<String>) {
    val style = ClassLoader.getSystemClassLoader().getResource("style.css")?.readText()
    embeddedServer(Netty, port = 80) {
        routing {
            get("/") {
                call.respondRedirect("/display")
            }
            get("/display") {
                call.respondHtml(HttpStatusCode.OK) {
                    head {
                        title {
                            +"Display .obj"
                        }
                        style {
                            +style!!
                        }
                    }
                    body {
                        form(
                            encType = FormEncType.multipartFormData,
                            action = "/display",
                            method = FormMethod.post
                        ) {
                            div {
                                unsafe {
                                    +"<label for=\"upload-model\">Browse file...</label>"
                                }
                                input(type = InputType.file) {
                                    id = "upload-model"
                                    name = "upload-model"
                                    accept = ".obj"
                                    onChange = "form.submit();"
                                }
                            }
                        }
                    }
                }
            }
        }
    }.start(wait = true)
}