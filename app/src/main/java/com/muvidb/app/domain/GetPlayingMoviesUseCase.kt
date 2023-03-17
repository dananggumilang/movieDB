package com.muvidb.app.domain

import com.muvidb.app.base.arch.BaseUseCase
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.data.network.model.mapper.MoviesMapper
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.ui.viewparam.MovieViewParam
import com.muvidb.app.utils.ext.suspendSubscribe
import com.muvidb.app.utils.mapper.ListMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPlayingMoviesUseCase(
    private val movieRepository: MovieRepository,
    dispatcher: CoroutineDispatcher
) : BaseUseCase<Nothing, List<MovieViewParam>>(dispatcher) {

    override suspend fun execute(param: Nothing?): Flow<ViewResource<List<MovieViewParam>>> {
        return flow {
            emit(ViewResource.Loading())
            movieRepository.getPlayingMovies().collect {
                it.suspendSubscribe(
                    doOnSuccess = { response ->
                        emit(ViewResource.Success(ListMapper(MoviesMapper).toViewParams(response.data?.results)))
                    },
                    doOnError = { error ->
                        emit(ViewResource.Error(error.exception))
                    }
                )
            }
        }
    }

}