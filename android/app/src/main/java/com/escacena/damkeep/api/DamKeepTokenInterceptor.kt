package com.escacena.damkeep.api

import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MySharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DamKeepTokenInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request
        val token = MySharedPreferencesManager().getSharedPreferences()
            .getString(Constants.SHARED_PREFERENCES_TOKEN_KEYWORD, "")

        val requestBuilder: Request.Builder =
            original.newBuilder().header("Authorization", "Bearer " + token)
        request = requestBuilder.build()


        return chain.proceed(request)

    }
}