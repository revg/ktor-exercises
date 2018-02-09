import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.apache.*
import io.ktor.client.response.*
import io.ktor.content.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        val client = HttpClient(Apache)
        val wikipediaLang = "en"

        intercept(ApplicationCallPipeline.Call) {
            val result = client.call("https://$wikipediaLang.wikipedia.org${call.request.uri}")
            val proxiedHeaders = result.response.headers
            val location = proxiedHeaders["Location"]
            val contentType = proxiedHeaders["Content-Type"]

            fun String.stripWikipediaDomain() = this.replace(Regex("(https?:)?//\\w+\\.wikipedia\\.org"), "")

            // Propagates location header, removing wikipedia domain from it
            if (location != null) {
                call.response.header("Location", location.stripWikipediaDomain())
            }

            when {
                (contentType ?: "").startsWith("text/html") -> {
                    val text = result.response.readText()
                    val filteredText = text.stripWikipediaDomain()
                    call.respond(
                        TextContent(
                            filteredText,
                            ContentType.Text.Html.withCharset(Charsets.UTF_8),
                            result.response.status
                        )
                    )
                }
                else -> {
                    call.respond(result.response.status, ByteArrayContent(result.response.readBytes()))
                }
            }
        }
    }
    server.start(wait = true)
}
