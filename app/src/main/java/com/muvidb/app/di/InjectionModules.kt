package com.muvidb.app.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.muvidb.app.data.network.service.ApiService
import com.muvidb.app.data.network.datasource.MovieNetworkDataSource
import com.muvidb.app.data.network.datasource.MovieNetworkDataSourceImpl
import com.muvidb.app.data.repository.MovieRepository
import com.muvidb.app.data.repository.MovieRepositoryImpl
import com.muvidb.app.domain.GetAllMoviesUseCase
import com.muvidb.app.domain.GetDetailMovieUseCase
import com.muvidb.app.domain.GetMovieByGenreUseCase
import com.muvidb.app.ui.feature.detailmovie.DetailMovieViewModel
import com.muvidb.app.ui.feature.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object InjectionModules {
    fun getModules() = listOf(database, network, dataSource, repository, useCases, viewModels)

    private val database = module {

    }

    private val network = module {
        single { ChuckerInterceptor.Builder(androidContext()).build() }
        single { ApiService.invoke(get()) }
    }

    private val dataSource = module {
        single<MovieNetworkDataSource> { MovieNetworkDataSourceImpl(get()) }
    }

    private val repository = module {
        single<MovieRepository> { MovieRepositoryImpl(get()) }
    }

    private val useCases = module {
        single { GetAllMoviesUseCase(get(), Dispatchers.IO) }
        single { GetDetailMovieUseCase(get(), Dispatchers.IO) }
        single { GetMovieByGenreUseCase(get(), Dispatchers.IO) }
    }

    private val viewModels = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::DetailMovieViewModel)
    }

}