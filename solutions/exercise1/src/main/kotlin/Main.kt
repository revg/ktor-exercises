import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.sessions.*
import io.ktor.util.*
import kotlinx.html.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        install(Sessions) {
            cookie<MySession>("EXERCISE1_SESSION", SessionStorageMemory())
        }
        routing {
            homeRoute()
            loginRoute()
        }
    }
    server.start(wait = true)
}

data class MySession(val user: String)

fun Routing.homeRoute() {
    get("/") {
        val session = call.sessions.get<MySession>()
        call.respondHtml {
            body {
                h1 {
                    +"Hello ${(session?.user ?: "World!").escapeHTML()}"
                }
                p {
                    a(href = "/login") { +"Login" }
                }
            }
        }
    }
}

fun Routing.loginRoute() {
    get("/login") {
        call.respondHtml {
            body {
                form(action = "/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
                    div {
                        +"Username:"
                        textInput(name = "username") { }
                    }
                    div {
                        +"Password:"
                        passwordInput(name = "password") { }
                    }
                    div {
                        submitInput()
                    }
                }
            }
        }
    }

    post("/login") {
        val post = call.receiveOrNull() ?: Parameters.Empty
        val username = post["username"]
        val password = post["password"]

        if (username != null && username.isNotBlank() && username == password) {
            call.sessions.set(MySession(username))
            call.respondRedirect("/")
        } else {
            call.respondHtml {
                body {
                    +"Invalid credentials. "
                    a(href = "/login") { +"Retry?" }
                }
            }
        }
    }

    // Version using authentication feature
    /*
    route("/login", HttpMethod.Post) {
        authentication {
            formAuthentication("username", "password", FormAuthChallenge.Redirect { call, credentials -> "/login" }) { credentials ->
                if (credentials.name == credentials.password) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
        post {
            val principal = call.authentication.principal<UserIdPrincipal>()
            call.sessions.set(MySession(principal!!.name))
            call.respondRedirect("/")
        }
    }
    */
}