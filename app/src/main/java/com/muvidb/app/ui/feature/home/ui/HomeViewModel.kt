package com.muvidb.app.ui.feature.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.domain.GetPlayingMoviesUseCase
import com.muvidb.app.domain.GetPopularMoviesUseCase
import com.muvidb.app.domain.GetUpComingMoviesUseCase
import com.muvidb.app.ui.viewparam.MovieViewParam
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPlayingMoviesUseCase: GetPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpComingMoviesUseCase: GetUpComingMoviesUseCase
) : ViewModel() {

    val getPlayingMoviesResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()
    val getPopularMoviesResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()
    val getUpComingMoviesResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()

    fun getPlayingMovies() {
        viewModelScope.launch {
            getPlayingMoviesUseCase().collect {
                getPlayingMoviesResult.postValue(it)
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().collect {
                getPopularMoviesResult.postValue(it)
            }
        }
    }

    fun getUpComingMovies() {
        viewModelScope.launch {
            getUpComingMoviesUseCase().collect {
                getUpComingMoviesResult.postValue(it)
            }
        }
    }

}