package com.muvidb.app.ui.viewparam

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieViewParam(
    val id: Int,
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val original_title: String,
    val original_language: String,
    val title: String,
    val backdrop_path: String,
    val popularity: Double,
    val vote_count: Int,
    val video: Boolean,
    val vote_average: Double,
    var isFavorite: Boolean = false
) : Parcelable