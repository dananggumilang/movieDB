package com.muvidb.app.data.repository

import com.muvidb.app.base.arch.Repository
import com.muvidb.app.base.wrapper.DataResource
import com.muvidb.app.data.local.datasource.MovieLocalDataSource
import com.muvidb.app.data.local.entity.MovieEntity
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPlayingMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getPopularMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getUpComingMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getMoviesByGenres(genreId: Int): Flow<DataResource<MovieResponse>>
    suspend fun getMovieTrailers(movieId: Int): Flow<DataResource<MovieResponse>>

    suspend fun getFavoriteQuotes(): Flow<DataResource<List<MovieEntity>>>
}

class MovieRepositoryImpl(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : Repository(), MovieRepository {

    override suspend fun getPlayingMovies(): Flow<DataResource<MovieResponse>> = flow {
        println(movieNetworkDataSource.getPlayingMovies())
        emit(safeNetworkCall { movieNetworkDataSource.getPlayingMovies() })
    }

    override suspend fun getPopularMovies(): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getPopularMovies() })
    }

    override suspend fun getUpComingMovies(): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getUpComingMovies() })
    }

    override suspend fun getMoviesByGenres(genreId: Int): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getMoviesByGenres(genreId) })
    }

    override suspend fun getMovieTrailers(movieId: Int): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getMovieTrailers(movieId) })
    }

    override suspend fun getFavoriteQuotes(): Flow<DataResource<List<MovieEntity>>> = flow {
        emit(proceed { movieLocalDataSource.getFavouriteMovie() })
    }

}