package software_engineering

import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.html.*
import java.io.ByteArrayOutputStream

fun main() {
    embeddedServer(Netty, port = 80, module = Application::module).start(wait = true)
}

fun Application.module() {
    configureRouting()
}

fun Application.configureRouting() {
    val style = ClassLoader.getSystemClassLoader().getResource("style.css")?.readText()
    val viewFileFunction = ClassLoader.getSystemClassLoader().getResource("display.js")?.readText()
    routing {
        get("/*") {
            withContext(Dispatchers.IO) {
                call.respondHtml(HttpStatusCode.NotFound) {
                    head {
                        title {
                            +"Display .obj"
                        }
                        style {
                            +style!!
                        }
                    }
                    body {
                        div {
                            +"Page not found (404)"
                        }
                    }
                }
            }
        }
        get("/") {
            withContext(Dispatchers.IO) {
                call.respondRedirect("/display")
            }
        }
        get("/display") {
            withContext(Dispatchers.IO) {
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
        post("/display") {
            withContext(Dispatchers.IO) {
                val byteArrayOutputStream = ByteArrayOutputStream()
                val multipart = call.receiveMultipart()
                multipart.forEachPart { part ->
                    if (part is PartData.FileItem) {
                        val fileBytes = part.streamProvider().readBytes()
                        byteArrayOutputStream.writeBytes(fileBytes)
                    }
                    part.dispose()
                }
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
                        script(src = "https://cdn.jsdelivr.net/npm/three@0.116.1/build/three.js") {}
                        script(src = "https://cdn.jsdelivr.net/npm/three@0.116.1/examples/js/loaders/OBJLoader.js") {}
                        script {
                            +viewFileFunction!!
                        }
                        script(type = "module") {
                            +"display(`$byteArrayOutputStream`);"
                        }
                    }
                }
            }
        }
    }
}