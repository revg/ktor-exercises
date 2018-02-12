import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import java.time.*
import java.util.*


val Number.ms get() = Duration.ofMillis(this.toLong())


fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        val movieRepository = DelayedMovieRepository(DummyMovieRepository, delay = 2000.ms)
        install(Locations)
        install(StatusPages) {
            this.exception<NoSuchElementException> {
                call.respondHtml(HttpStatusCode.NotFound) {
                    body {
                        h1 { +"404 Not Found" }
                        p { +"${it.message}" }
                    }
                }
            }
        }
        routing {
            homeRoute(movieRepository)
            movieRoute(movieRepository)
        }
    }
    server.start(wait = true)
}

@Location("/")
object RootLocation

@Location("/movie/{name}")
data class MovieLocation(val name: String)

fun Routing.homeRoute(movieRepository: MovieRepository) {
    get<RootLocation> {
        val movies = movieRepository.getMovieNames()

        call.respondHtml {
            body {
                ul {
                    for (movie in movies) {
                        li {
                            a(href = call.locations.href(MovieLocation(name = movie))) {
                                +movie
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Routing.movieRoute(movieRepository: MovieRepository) {
    get<MovieLocation> { location ->
        val summary = movieRepository.getMovieSummary(location.name)
                ?: throw NoSuchElementException("Can't find movie with name ${location.name}")
        call.respondHtml {
            body {
                h1 { +location.name }
                p {
                    +summary
                }
            }
        }
    }
}