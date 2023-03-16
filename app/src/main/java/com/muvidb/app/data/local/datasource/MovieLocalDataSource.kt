package com.muvidb.app.data.local.datasource

import com.muvidb.app.data.local.dao.MovieDao
import com.muvidb.app.data.local.entity.MovieEntity

interface MovieLocalDataSource {
    suspend fun getFavouriteMovie() : List<MovieEntity>
    suspend fun getFavouriteMovieById(id : Int?): MovieEntity?
    suspend fun addFavourite(movieEntity: MovieEntity): Long
    suspend fun deleteFavourite(movieEntity: MovieEntity): Int
}

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun getFavouriteMovie(): List<MovieEntity> = movieDao.getFavoriteQuotes()
    override suspend fun getFavouriteMovieById(id: Int?): MovieEntity? = movieDao.getFavoriteQuotesByID(id)
    override suspend fun addFavourite(movieEntity: MovieEntity): Long = movieDao.addFavoriteQuote(movieEntity)
    override suspend fun deleteFavourite(movieEntity: MovieEntity): Int = movieDao.removeFavoriteQuote(movieEntity)
}

