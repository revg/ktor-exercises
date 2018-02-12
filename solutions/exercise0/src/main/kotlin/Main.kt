import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            plainTextRoute()
            htmlRoute()
        }
    }
    server.start(wait = true)
}

fun Routing.plainTextRoute() {
    get("/plain") {
        call.respondText("Hello World!")
    }
}

fun Routing.htmlRoute() {
    get("/html") {
        call.respondText("<h1>Hello World!</h1>", contentType = ContentType.Text.Html)
    }
}