package com.muvidb.app.data.local.dao

import androidx.room.*
import com.muvidb.app.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    suspend fun getFavoriteMovies(): List<MovieEntity>

    @Query("SELECT * FROM movies WHERE id == :id LIMIT 1")
    suspend fun getFavoriteMoviesByID(id : Int?): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(note: MovieEntity): Long

    @Delete
    suspend fun removeFavoriteMovie(note: MovieEntity): Int
}