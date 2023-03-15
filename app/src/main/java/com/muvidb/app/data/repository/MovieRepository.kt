package com.muvidb.app.data.repository

import com.muvidb.app.base.arch.Repository
import com.muvidb.app.base.wrapper.DataResource
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.model.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPlayingMovies(): Flow<DataResource<List<MovieResponse>>>
    suspend fun getPopularMovies() : Flow<DataResource<List<MovieResponse>>>
    suspend fun getUpComingMovies() : Flow<DataResource<List<MovieResponse>>>
    suspend fun getMoviesByGenres(genreId: Int): Flow<DataResource<List<MovieResponse>>>
    suspend fun getMovieTrailers(movieId: Int): Flow<DataResource<List<MovieResponse>>>
}

class MovieRepositoryImpl(private val movieNetworkDataSource: MovieNetworkDataSource) : Repository(), MovieRepository {

    override suspend fun getPlayingMovies(): Flow<DataResource<List<MovieResponse>>> = flow {
        emit( safeNetworkCall { movieNetworkDataSource.getPlayingMovies() })
    }

    override suspend fun getPopularMovies(): Flow<DataResource<List<MovieResponse>>> = flow {
        emit( safeNetworkCall { movieNetworkDataSource.getPopularMovies() })
    }

    override suspend fun getUpComingMovies(): Flow<DataResource<List<MovieResponse>>> = flow {
        emit( safeNetworkCall { movieNetworkDataSource.getUpComingMovies() })
    }

    override suspend fun getMoviesByGenres(genreId: Int): Flow<DataResource<List<MovieResponse>>> = flow {
        emit( safeNetworkCall { movieNetworkDataSource.getMoviesByGenres(genreId) })
    }

    override suspend fun getMovieTrailers(movieId: Int): Flow<DataResource<List<MovieResponse>>> = flow {
        emit( safeNetworkCall { movieNetworkDataSource.getMovieTrailers(movieId) })
    }

}