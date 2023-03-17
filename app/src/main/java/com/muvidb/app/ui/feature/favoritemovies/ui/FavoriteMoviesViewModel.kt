package com.muvidb.app.ui.feature.favoritemovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muvidb.app.base.wrapper.ViewResource
import com.muvidb.app.domain.GetFavouriteMoviesUseCase
import com.muvidb.app.ui.viewparam.MovieViewParam
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase) : ViewModel() {

    val getFavouriteMoviesResult = MutableLiveData<ViewResource<List<MovieViewParam>>>()

    fun getFavouriteMovies() {
        viewModelScope.launch {
            getFavouriteMoviesUseCase().collect {
                getFavouriteMoviesResult.value = it
            }
        }
    }

}