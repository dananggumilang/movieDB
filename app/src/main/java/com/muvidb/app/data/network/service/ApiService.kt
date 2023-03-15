package com.muvidb.app.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.muvidb.app.data.network.model.response.MovieResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("3/movie/now_playing")
    suspend fun getPlayingMovies(): List<MovieResponse>

    @GET("3/movie/popular")
    suspend fun getPopularMovies(): List<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(): List<MovieResponse>

    @GET("3/movie/{id}/videos")
    suspend fun getMovieVideos(@Path("id") movieId: Int): List<MovieResponse>

    @GET("3/discover/movie/{id}")
    suspend fun getMoviesByGenre(@Path("id") genreId: Int): List<MovieResponse>

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(RequestInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}