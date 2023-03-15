package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlayingMoviesUseCase(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, MovieViewParam>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<MovieViewParam>> {
        return flow {
            emit(ViewResource.Loading())
            movieRepository.getPlayingMovies().collect {
                it.suspendSubscribe(
                    doOnSuccess = { response ->

                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }

}