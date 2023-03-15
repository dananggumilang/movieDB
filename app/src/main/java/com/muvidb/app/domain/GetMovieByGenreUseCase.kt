package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.response.MovieResponse
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieByGenreUseCase(private val movieRepository: MovieRepository, dispatcher: CoroutineDispatcher) : BaseUseCase<Nothing, MovieResponse>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<MovieResponse>> {
        return flow {
            emit(ViewResource.Loading())
            movieRepository.getAllMovies().collect {
                it.suspendSubscribe(
                    doOnSuccess = {

                    },
                    doOnError = {

                    }
                )
            }
        }
    }

}