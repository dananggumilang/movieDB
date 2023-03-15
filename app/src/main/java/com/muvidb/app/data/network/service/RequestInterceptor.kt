package com.muvidb.app.data.network.service

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val httpUrl = originalRequest.url
        val url = httpUrl.newBuilder()
            .addQueryParameter("api_key", "ad63f14521edf69e69e69f1fa474dfd8")
            .build()
        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}