package com.mafraq.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mafraq.BuildConfig
import com.mafraq.Constants
import com.mafraq.data.remote.interceptor.HeaderInterceptor
import com.mafraq.data.remote.models.LocationRemote
import com.mafraq.data.remote.service.RetableService
import com.mafraq.presentation.utils.extensions.toStringUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.mafraq.data.remote.service.LocationDeserializer

@Module
@InstallIn(SingletonComponent::class)
object RemoteApiModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .disableHtmlEscaping()
            .registerTypeAdapter(LocationRemote::class.java, LocationDeserializer())
            .create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .addInterceptor(loggingInterceptor)
        .readTimeout(timeout = TIMEOUT_SECONDS, unit = TimeUnit.SECONDS)
        .callTimeout(timeout = TIMEOUT_SECONDS, unit = TimeUnit.SECONDS)
        .writeTimeout(timeout = TIMEOUT_SECONDS, unit = TimeUnit.SECONDS)
        .connectTimeout(timeout = TIMEOUT_SECONDS, unit = TimeUnit.SECONDS)
        .followRedirects(followRedirects = false)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL.toStringUrl(path = Constants.API_VERSION))
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor =
        HeaderInterceptor(BuildConfig.RETABLE_API_KEY)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    @Singleton
    @Provides
    fun provideCCHApiService(retrofit: Retrofit): RetableService =
        retrofit.create(RetableService::class.java)

    private const val TIMEOUT_SECONDS: Long = 45
}
