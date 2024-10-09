package com.mynewchallenge.data.service

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("AuthToken", "Auth-Key")
            .build()

        return chain.proceed(request)
    }
}