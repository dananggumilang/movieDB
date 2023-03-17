package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.mapper.AddFavouriteMovieMapper
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class DeleteFavoriteMovieUseCase(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<MovieViewParam, MovieViewParam?>(dispatcher) {

    override suspend fun execute(param: MovieViewParam?): Flow<ViewResource<MovieViewParam?>> = flow {
        param?.let { p ->
            param.apply { isFavorite = false }
            movieRepository.deleteFavoriteMovie(AddFavouriteMovieMapper.toViewParam(p)).first().suspendSubscribe(
                doOnSuccess = {
                    emit(ViewResource.Success(param))
                    println("KEPANGGIL $param")
                },
                doOnError = { error ->
                    emit(ViewResource.Error(error.exception))
                }
            )
        }

    }
}