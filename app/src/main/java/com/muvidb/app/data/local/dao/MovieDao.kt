package com.muvidb.app.data.local.dao

import androidx.room.*
import com.muvidb.app.data.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM quotes")
    suspend fun getFavoriteQuotes(): List<MovieEntity>

    @Query("SELECT * FROM quotes WHERE id == :id LIMIT 1")
    suspend fun getFavoriteQuotesByID(id : Int?): MovieEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteQuote(note: MovieEntity): Long

    @Delete
    suspend fun removeFavoriteQuote(note: MovieEntity): Int
}