interface MovieRepository {
    suspend fun getMovieNames(): List<String>
    suspend fun getMovieSummary(name: String): String?
}