package com.muvidb.app.data.repository

import com.muvidb.app.base.arch.Repository
import com.muvidb.app.base.wrapper.DataResource
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.response.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getAllMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getDetailMovieById(): Flow<DataResource<MovieResponse>>
    suspend fun getMoviesByGenres(): Flow<DataResource<MovieResponse>>
}

class MovieRepositoryImpl(private val movieNetworkDataSource: MovieNetworkDataSource) : Repository(), MovieRepository {

    override suspend fun getAllMovies(): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getAllMovies() })
    }

    override suspend fun getDetailMovieById(): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getAllMovies() })
    }

    override suspend fun getMoviesByGenres(): Flow<DataResource<MovieResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getAllMovies() })
    }

}