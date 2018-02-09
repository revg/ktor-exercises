# Exercise 2: HTML DSL, Typed Routes and Asynchronous Code

Create a simple asynchronous interface using **suspend** functions to retrieve a list of movies and a movie summaries.
And a simple implementation returning constant values and simulating database/API latency creating
a non-blocking **delay** when using that interface.

Create an HTML unordererd list using kotlinx.html DSL with movie listing in `GET /` route.

Create a `/movie/:name` route displaying movie's summary or a page returning HTTP 404 error code when not found.

Use `@Location data class` to build and handle routes in a typed way.  

## Proposed steps for this exercise:

* Create `interface MovieRepository { suspend fun getMovieNames(): List<String>; suspend fun getMovieSummary(name: String): String }`
* Create a class that implements that interface returning fake results: `return listOf()`, and `return when (name) { ... }`
* Add a delay of 4000 milliseconds to each call in that class or in a separate class DelayedMovieRepository(val original: MovieRepository) : MovieRepository
* Instantiate the implementation with the delay in your main and propagate it to your GET / route, get movie list into a local and then iterate over it to generate a `<ul><li><a href=”/movie/$name”>$name</a></li>...</ul>`
* Create a route GET /movie/:name and using the repository display the name of the movie and its summary
* Change things to use @Location for typing movie route
