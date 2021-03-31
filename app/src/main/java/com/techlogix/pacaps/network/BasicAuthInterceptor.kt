package com.techlogix.pacaps.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val credentials = Credentials.basic("rzp_test_EmV2COwoczSMmV", "xtKutCT8FGnWtDLYjYeY1YZz")
        val request = chain.request();
        val authenticatedRequest =
            request.newBuilder().addHeader("Authorization", credentials).build()
        return chain.proceed(authenticatedRequest)
    }
}