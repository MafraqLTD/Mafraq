package com.mafraq.data.remote.service

import com.mafraq.data.BuildConfig
import com.mafraq.data.remote.models.ApiResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetableService {

    @GET(DRIVER_PATH)
    suspend fun getDrivers(): Response<ApiResponseDto>

    @GET(DRIVER_PATH)
    suspend fun getDriver(
        @Query("row_id")
        driverId: String
    ): Response<ApiResponseDto>

    companion object {
        private const val PREFIX = "public/retable/"
        private const val SUFFIX = "/data"
        const val DRIVER_PATH = "${PREFIX}${BuildConfig.RETABLE_DRIVER_TABLE_ID}${SUFFIX}"
    }
}
