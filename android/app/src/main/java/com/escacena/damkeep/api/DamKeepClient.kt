package com.escacena.damkeep.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.escacena.damkeep.common.Constants
import com.escacena.damkeep.common.MyApp
import com.escacena.damkeep.common.MySharedPreferencesManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Module
class DamKeepClient {

    @Singleton
    @Provides
    @Named("url")
    fun provideBaseUrl(): String = Constants.API_BASE_URL


    @Singleton
    @Provides
    fun createClient(@Named("url") baseUrl: String): DamKeepService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor(DamKeepTokenInterceptor()).build())
            .build()
            .create(DamKeepService::class.java)
    }
}