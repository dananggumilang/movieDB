package com.muvidb.app.data.network.datasource

import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.network.service.ApiService

interface MovieNetworkDataSource {
    suspend fun getPlayingMovies() : MovieResponse
    suspend fun getPopularMovies() : MovieResponse
    suspend fun getUpComingMovies() : MovieResponse
    suspend fun getMoviesByGenres(genreId: Int) : MovieResponse
    suspend fun getMovieTrailers(movieId: Int) : MovieResponse
}

class MovieNetworkDataSourceImpl(private val service: ApiService) : MovieNetworkDataSource {
    override suspend fun getPlayingMovies(): MovieResponse = service.getPlayingMovies()
    override suspend fun getPopularMovies(): MovieResponse = service.getPopularMovies()
    override suspend fun getUpComingMovies(): MovieResponse = service.getUpComingMovies()
    override suspend fun getMoviesByGenres(genreId: Int): MovieResponse = service.getMoviesByGenre(genreId)
    override suspend fun getMovieTrailers(movieId: Int): MovieResponse = service.getMovieVideos(movieId)
}