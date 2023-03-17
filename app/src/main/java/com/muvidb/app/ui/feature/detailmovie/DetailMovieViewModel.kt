package com.muvidb.app.ui.feature.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.domain.*
import com.muvidb.app.ui.viewparam.MovieViewParam
import kotlinx.coroutines.launch

class DetailMovieViewModel(
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val getFavouriteMovieByIdUseCase: GetFavouriteMovieByIdUseCase,
    private val getKeyMovieTrailerUseCase: GetKeyMovieTrailerUseCase
) : ViewModel() {

    val addFavoriteResult = MutableLiveData<ViewResource<MovieViewParam?>>()
    val removeFavoriteResult = MutableLiveData<ViewResource<MovieViewParam?>>()
    val getFavouriteMovieByIdResult = MutableLiveData<ViewResource<MovieViewParam?>>()
    val getKeyMovieTrailerResult = MutableLiveData<ViewResource<String>>()

    fun addFavoriteMovie(movieViewParam: MovieViewParam?) {
        viewModelScope.launch {
            addFavoriteMovieUseCase(movieViewParam).collect {
                addFavoriteResult.value = it
            }
        }
    }

    fun deleteFavoriteMovie(movieViewParam: MovieViewParam?) {
        viewModelScope.launch {
            deleteFavoriteMovieUseCase(movieViewParam).collect {
                removeFavoriteResult.value = it
            }
        }
    }

    fun getFavouriteMovieById(id: Int) {
        viewModelScope.launch {
            getFavouriteMovieByIdUseCase(id).collect {
                getFavouriteMovieByIdResult.value = it
            }
        }
    }

    fun getKeyMovieTrailer(id: Int) {
        viewModelScope.launch {
            getKeyMovieTrailerUseCase(id).collect {
                getKeyMovieTrailerResult.postValue(it)
            }
        }
    }

}