package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.mapper.FavouriteMoviesMapper
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.suspendSubscribe
import com.muvidb.app.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetFavouriteMoviesUseCase(private val movieRepository: MovieRepository, dispatcher: CoroutineDispatcher): BaseUseCase<Nothing, List<MovieViewParam>>(dispatcher) {
    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> = flow {
        movieRepository.getFavoriteMovies().first().suspendSubscribe(
            doOnSuccess = { result ->
                emit(ViewResource.Success(ListMapper(FavouriteMoviesMapper).toViewParams(result.data)))
            },
            doOnError = { error ->
                emit(ViewResource.Error(error.exception))
            }
        )
    }
}