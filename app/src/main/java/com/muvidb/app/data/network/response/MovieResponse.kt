package com.muvidb.app.data.network.response

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDetailResponse(
    var id: Int,
    var backdrop_path: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var genres: List<MovieGenre>? = null,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)

data class Movie(
    val id: Int,
    var poster_path: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var original_title: String? = null,
    var original_language: String? = null,
    var title: String? = null,
    var backdrop_path: String? = null,
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var vote_average: Double

)

data class MovieVideoResponse(
    var id: Int,
    var results: List<MovieVideo>
)

data class MovieVideo(
    var id: String,
    var key: String,
    var name: String,
    var site: String
)

data class MovieGenre(
    var genresId: Int,
    var name: String?
)