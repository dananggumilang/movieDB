package com.muvidb.app.data.repository

import com.muvidb.app.base.arch.Repository
import com.muvidb.app.base.wrapper.DataResource
import com.muvidb.app.data.local.datasource.MovieLocalDataSource
import com.muvidb.app.data.local.entity.MovieEntity
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.network.model.response.VideosResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    suspend fun getPlayingMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getPopularMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getUpComingMovies(): Flow<DataResource<MovieResponse>>
    suspend fun getMoviesByGenres(genreId: Int): Flow<DataResource<MovieResponse>>
    suspend fun getKeyMovieTrailers(movieId: Int): Flow<DataResource<VideosResponse>>

    suspend fun getFavoriteMovies(): Flow<DataResource<List<MovieEntity>>>
    suspend fun addFavoriteMovie(entity: MovieEntity): Flow<DataResource<Long>>
    suspend fun getFavoriteMovieById(id: Int?): Flow<DataResource<MovieEntity?>>
    suspend fun deleteFavoriteMovie(entity: MovieEntity): Flow<DataResource<Int>>
}

class MovieRepositoryImpl(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val movieLocalDataSource: MovieLocalDataSource
) : Repository(), MovieRepository {

    override suspend fun getPlayingMovies(): Flow<DataResource<MovieResponse>> = flow {
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

    override suspend fun getKeyMovieTrailers(movieId: Int): Flow<DataResource<VideosResponse>> = flow {
        emit(safeNetworkCall { movieNetworkDataSource.getKeyMovieTrailers(movieId) })
    }

    override suspend fun getFavoriteMovies(): Flow<DataResource<List<MovieEntity>>> = flow {
        emit(proceed { movieLocalDataSource.getFavouriteMovies() })
    }

    override suspend fun addFavoriteMovie(entity: MovieEntity): Flow<DataResource<Long>> = flow {
        emit(proceed { movieLocalDataSource.addFavourite(entity) })
    }

    override suspend fun getFavoriteMovieById(id: Int?): Flow<DataResource<MovieEntity?>> = flow {
        emit(proceed { movieLocalDataSource.getFavouriteMovieById(id) })
    }

    override suspend fun deleteFavoriteMovie(entity: MovieEntity): Flow<DataResource<Int>> = flow {
        emit(proceed { movieLocalDataSource.deleteFavourite(entity) })
    }

}