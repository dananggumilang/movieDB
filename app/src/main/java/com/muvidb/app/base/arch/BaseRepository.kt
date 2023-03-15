package com.muvidb.app.base.arch

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.muvidb.app.base.wrapper.DataResource
import com.muvidb.app.base.exception.ApiErrorException
import com.muvidb.app.base.exception.NoInternetConnectionException
import com.muvidb.app.base.exception.UnexpectedErrorException
import com.muvidb.app.data.network.response.MovieResponse
import okhttp3.ResponseBody
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    abstract fun <T> getErrorMessageFromApi(response : T) : String

    suspend fun <T> safeNetworkCall(apiCall: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> DataResource.Error(NoInternetConnectionException())
                is HttpException -> {
                    DataResource.Error(ApiErrorException(getErrorMessageFromApi(throwable.response()?.errorBody()), throwable.code()))
                }
                else -> {
                    DataResource.Error(UnexpectedErrorException())
                }
            }
        }
    }

    suspend fun <T> proceed(coroutine: suspend () -> T): DataResource<T> {
        return try {
            DataResource.Success(coroutine.invoke())
        } catch (exception: Exception) {
            DataResource.Error(exception)
        }
    }
}

open class Repository : BaseRepository() {
    private val gson: Gson by inject(Gson::class.java)

    override fun <T> getErrorMessageFromApi(response: T): String {
        val responseBody = response as ResponseBody
        return try {
            val body = gson.fromJson(responseBody.string(), MovieResponse::class.java)
            "Error Api"
        } catch (e: JsonParseException) {
            "Error Api"
        }
    }
}