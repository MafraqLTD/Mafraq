package com.mafraq.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mafraq.data.BuildConfig
import com.mafraq.data.entities.SeparatedValuesListOfString
import com.mafraq.data.remote.interceptor.HeaderInterceptor
import com.mafraq.data.remote.models.LocationRemote
import com.mafraq.data.remote.service.DirectionsApiService
import com.mafraq.data.remote.service.RetableService
import com.mafraq.data.utils.Constants
import com.mafraq.data.utils.convertors.LocationDeserializer
import com.mafraq.data.utils.convertors.SeparatedValuesListOfStringDeserializer
import com.mafraq.data.utils.toStringUrl
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
            .registerTypeAdapter(SeparatedValuesListOfString::class.java, SeparatedValuesListOfStringDeserializer())
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
    fun provideRetableApiService(retrofit: Retrofit): RetableService =
        retrofit.create(RetableService::class.java)

    @Singleton
    @Provides
    fun provideDirectionsApiService(retrofit: Retrofit): DirectionsApiService =
        retrofit.newBuilder()
            .baseUrl(BuildConfig.ROUTES_BASE_URL)
            .build()
            .create(DirectionsApiService::class.java)

    private const val TIMEOUT_SECONDS: Long = 45
}
