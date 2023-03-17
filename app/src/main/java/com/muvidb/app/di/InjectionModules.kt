package com.muvidb.app.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.muvidb.app.data.local.MovieDatabase
import com.muvidb.app.data.local.datasource.MovieLocalDataSource
import com.muvidb.app.data.local.datasource.MovieLocalDataSourceImpl
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.datasource.MovieNetworkDataSourceImpl
import com.muvidb.app.data.network.service.ApiService
import com.muvidb.app.data.network.service.RequestInterceptor
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.data.repository.MovieRepositoryImpl
import com.muvidb.app.domain.*
import com.muvidb.app.ui.feature.detailmovie.DetailMovieViewModel
import com.muvidb.app.ui.feature.favoritemovies.ui.FavoriteMoviesViewModel
import com.muvidb.app.ui.feature.home.ui.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object InjectionModules {
    fun getModules() = listOf(database, network, dataSource, repository, useCases, viewModels)

    private val database = module {
        single { get<MovieDatabase>().movieDao() }
        single { MovieDatabase.create(androidContext()) }
    }

    private val network = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { RequestInterceptor() }
        single { ApiService.invoke(get(), get()) }
    }

    private val dataSource = module {
        single<MovieNetworkDataSource> { MovieNetworkDataSourceImpl(get()) }
        single<MovieLocalDataSource> { MovieLocalDataSourceImpl(get()) }
    }

    private val repository = module {
        single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    }

    private val useCases = module {
        single { GetPlayingMoviesUseCase(get(), Dispatchers.IO) }
        single { GetFavouriteMovieByIdUseCase(get(), Dispatchers.IO) }
        single { GetPopularMoviesUseCase(get(), Dispatchers.IO) }
        single { GetUpComingMoviesUseCase(get(), Dispatchers.IO) }
        single { GetMoviesByGenreUseCase(get(), Dispatchers.IO) }
        single { GetKeyMovieTrailerUseCase(get(), Dispatchers.IO) }
        single { GetFavouriteMoviesUseCase(get(), Dispatchers.IO) }
        single { AddFavoriteMovieUseCase(get(), Dispatchers.IO) }
        single { DeleteFavoriteMovieUseCase(get(), Dispatchers.IO) }
    }

    private val viewModels = module {
        viewModelOf(::FavoriteMoviesViewModel)
        viewModelOf(::HomeViewModel)
        viewModelOf(::DetailMovieViewModel)
    }

}