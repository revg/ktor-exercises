import java.time.*


class DelayedMovieRepository(val base: MovieRepository, val delay: Duration = 4000.ms) : MovieRepository {
    private suspend fun intercept(method: String, args: Array<Any?>): MovieRepository {
        kotlinx.coroutines.experimental.time.delay(delay)
        return base
    }

    override suspend fun getMovieNames(): List<String> = intercept("getMovieNames", arrayOf()).getMovieNames()
    override suspend fun getMovieSummary(name: String): String? =
        intercept("getMovieSummary", arrayOf(name)).getMovieSummary(name)
}