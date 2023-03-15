package com.muvidb.app.data.network.datasource

import com.muvidb.app.data.network.service.ApiService
import com.muvidb.app.data.network.response.MovieResponse

interface MovieNetworkDataSource {
    suspend fun getAllMovies() : MovieResponse
    suspend fun getDetailMovieById() : MovieResponse
    suspend fun getMoviesByGenres() : MovieResponse
}

class MovieNetworkDataSourceImpl(private val service: ApiService) : MovieNetworkDataSource {
    override suspend fun getAllMovies() : MovieResponse = service.getPlayingMovies()
    override suspend fun getDetailMovieById() : MovieResponse = service.getPlayingMovies()
    override suspend fun getMoviesByGenres() : MovieResponse = service.getPlayingMovies()
}