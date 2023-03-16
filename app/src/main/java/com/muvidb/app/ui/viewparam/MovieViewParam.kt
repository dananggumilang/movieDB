package com.muvidb.app.ui.viewparam

data class MovieViewParam(
    val id: Int,
    var poster_path: String,
    var overview: String,
    var release_date: String,
    var original_title: String,
    var original_language: String,
    var title: String,
    var backdrop_path: String,
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var vote_average: Double,
    var isFavorite: Boolean = false
)