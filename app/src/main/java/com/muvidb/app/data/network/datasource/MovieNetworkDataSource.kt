package com.muvidb.app.data.network.datasource

import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.network.service.ApiService

interface MovieNetworkDataSource {
    suspend fun getPlayingMovies() : List<MovieResponse>
    suspend fun getPopularMovies() : List<MovieResponse>
    suspend fun getUpComingMovies() : List<MovieResponse>
    suspend fun getMoviesByGenres(genreId: Int) : List<MovieResponse>
    suspend fun getMovieTrailers(movieId: Int) : List<MovieResponse>
}

class MovieNetworkDataSourceImpl(private val service: ApiService) : MovieNetworkDataSource {
    override suspend fun getPlayingMovies(): List<MovieResponse> = service.getPlayingMovies()
    override suspend fun getPopularMovies(): List<MovieResponse> = service.getPopularMovies()
    override suspend fun getUpComingMovies(): List<MovieResponse> = service.getUpComingMovies()
    override suspend fun getMoviesByGenres(genreId: Int): List<MovieResponse> = service.getMoviesByGenre(genreId)
    override suspend fun getMovieTrailers(movieId: Int): List<MovieResponse> = service.getMovieVideos(movieId)
}