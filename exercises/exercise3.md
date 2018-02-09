# Exercise 3: Creating a Reverse Proxy

Create a Reverse Proxy that intercepts all requests, uses Ktor's HTTP client to mimic those requests to wikipedia and
sends the retrieved content to the client unmodified or replacing relevant URLs for html content.

## Proposed steps for this exercise:

* Create an interceptor that handles all the requests, get your path and respond the client with the requested path
* Use http client to request `https://en.wikipedia.org/$path` asynchronously, return its contents and propagate at least Content-Type
* Replace page contents if Content-Type is text/html: “https://en.wikipedia.org” -> “/” and later “//en.wikipedia.org” -> “/” or both at once using a regular expression
* Optional: try to pipe the contents to reduce memory usage
