package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.utils.ext.suspendSubscribe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetKeyMovieTrailerUseCase(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Int, String>(dispatcher) {

    override suspend fun execute(param: Int?): Flow<ViewResource<String>> = flow {
        param?.let { p ->
            emit(ViewResource.Loading())
            movieRepository.getKeyMovieTrailers(param).collect {
                it.suspendSubscribe(
                    doOnSuccess = { result ->
                        val keyMovieTrailer = result.data?.results?.filter { data ->
                            data?.name == "Official Trailer"
                        }?.get(0)?.key!!

                        emit(ViewResource.Success(keyMovieTrailer))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }

}