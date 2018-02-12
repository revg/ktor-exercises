object DummyMovieRepository : MovieRepository {
    override suspend fun getMovieNames(): List<String> {
        return listOf(
            "Movie1",
            "Movie2",
            "Movie3",
            "Movie4"
        )
    }

    override suspend fun getMovieSummary(name: String): String? {
        return when (name) {
            "Movie1" -> "Summary1"
            "Movie2" -> "Summary2"
            "Movie3" -> "Summary3"
            "Movie4" -> "Summary4"
            else -> null
        }
    }
}