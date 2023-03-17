package com.muvidb.app.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.muvidb.app.BuildConfig
import com.muvidb.app.data.network.model.response.MovieResponse
import com.muvidb.app.data.network.model.response.VideosResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("3/movie/now_playing")
    suspend fun getPlayingMovies() : MovieResponse

    @GET("3/movie/popular")
    suspend fun getPopularMovies() : MovieResponse

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies() : MovieResponse

    @GET("3/discover/movie/{id}")
    suspend fun getMoviesByGenre(@Path("genre_id") genreId: Int): MovieResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun getKeyMovieTrailer(@Path("movie_id") movieId: Int): VideosResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor, requestInterceptor: RequestInterceptor): ApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .addInterceptor(requestInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}