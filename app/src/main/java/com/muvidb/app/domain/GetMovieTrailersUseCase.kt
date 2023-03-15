package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetMovieTrailersUseCase(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<GetMovieTrailersUseCase.Param, MovieResponse>(dispatcher) {

    override suspend fun execute(param: Param?): Flow<ViewResource<MovieResponse>> {
        return flow {
            param?.let { p ->
                emit(ViewResource.Loading())
                movieRepository.getMovieTrailers(p.movieId).collect {
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

    data class Param(
        val movieId: Int
    )

}