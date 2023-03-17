package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.mapper.FavouriteMoviesMapper
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetFavouriteMovieByIdUseCase(private val movieRepository: MovieRepository, dispatcher: CoroutineDispatcher): BaseUseCase<Int, MovieViewParam?>(dispatcher) {
    override suspend fun execute(param: Int?): Flow<ViewResource<MovieViewParam?>> = flow {
        movieRepository.getFavoriteMovieById(param).first().suspendSubscribe(
            doOnSuccess = { result ->
                emit(ViewResource.Success(FavouriteMoviesMapper.toViewParam(result.data)))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}