package com.mafraq.data.remote.service

import com.mafraq.data.BuildConfig
import com.mafraq.data.remote.models.AdRemote
import com.mafraq.data.remote.models.ApiResponseRemote
import com.mafraq.data.remote.models.DriverRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetableService {

    @GET(DRIVER_PATH)
    suspend fun getDrivers(): Response<ApiResponseRemote<DriverRemote>>

    @GET(DRIVER_PATH)
    suspend fun getDriver(
        @Query("row_id")
        driverId: String
    ): Response<ApiResponseRemote<DriverRemote>>

    @GET(ADS_PATH)
    suspend fun getAds(): Response<ApiResponseRemote<AdRemote>>

    companion object {
        private const val PREFIX = "public/retable/"
        private const val SUFFIX = "/data"
        const val DRIVER_PATH = "${PREFIX}${BuildConfig.RETABLE_DRIVER_TABLE_ID}${SUFFIX}"
        const val ADS_PATH = "${PREFIX}${BuildConfig.RETABLE_ADS_TABLE_ID}${SUFFIX}"
    }
}
