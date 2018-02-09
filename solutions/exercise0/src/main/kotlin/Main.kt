import io.ktor.application.*
import io.ktor.html.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            rootRoute()
            htmlRoute()
        }
    }
    server.start(wait = true)
}

fun Routing.rootRoute() {
    get("/") {
        call.respondText("Hello World!")
    }
}

fun Routing.htmlRoute() {
    get("/html") {
        call.respondHtml {
            body {
                h1 { +"Hello World!" }
            }
        }
    }
}