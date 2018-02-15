package io.ktor.exercise4

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        watchPaths = listOf("io.ktor.exercise4"),
        module = Application::mymodule
    ).start(wait = true)
}

fun Application.mymodule() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
