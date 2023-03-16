package com.muvidb.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val poster_path: String,
    @ColumnInfo
    val overview: String,
    @ColumnInfo
    val release_date: String,
    @ColumnInfo
    val original_title: String,
    @ColumnInfo
    val original_language: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val backdrop_path: String,
    @ColumnInfo
    val popularity: Double,
    @ColumnInfo
    val vote_count: Int,
    @ColumnInfo
    val video: Boolean,
    @ColumnInfo
    val vote_average: Double,
    @ColumnInfo
    val isFavorite: Boolean = false
)