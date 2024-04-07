package com.mafraq.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val retableApiToken: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
            request = request.newBuilder()
                .addHeader("ApiKey", retableApiToken)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()

        return chain.proceed(request)
    }
}
