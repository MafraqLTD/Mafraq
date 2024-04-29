package com.mafraq.data.remote.service

import com.mafraq.data.BuildConfig
import com.mafraq.data.remote.models.AdRemote
import com.mafraq.data.remote.models.ApiResponseRemote
import com.mafraq.data.remote.models.DriverRemote
import com.mafraq.data.remote.models.EmployeeRemote
import com.mafraq.data.remote.models.InsertRowBody
import com.mafraq.data.remote.models.UpdateRowBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface RetableService {

    @GET(DRIVER_PATH)
    suspend fun getDrivers(): Response<ApiResponseRemote<DriverRemote>>

    @GET(DRIVER_PATH)
    suspend fun getDriver(
        @Query("row_id")
        driverId: String
    ): Response<ApiResponseRemote<DriverRemote>>

    @GET(EMPLOYEES_PATH)
    suspend fun getEmployees(): Response<ApiResponseRemote<EmployeeRemote>>

    @GET(ADS_PATH)
    suspend fun getAds(): Response<ApiResponseRemote<AdRemote>>

    @POST(CREATE_OR_UPDATE_EMPLOYEE_PATH)
    suspend fun createEmployee(
        @Body
        body: InsertRowBody
    ): Response<Any?>

    @PUT(CREATE_OR_UPDATE_EMPLOYEE_PATH)
    suspend fun updateEmployee(
        @Body
        body: UpdateRowBody
    ): Response<Any?>

    companion object {
        private const val PREFIX = "public/retable/"
        private const val SUFFIX = "/data"
        private const val BASE_URL_V1 = BuildConfig.BASE_URL + "v1/"
        const val ADS_PATH = "${PREFIX}${BuildConfig.RETABLE_ADS_TABLE_ID}${SUFFIX}"
        const val DRIVER_PATH = "${PREFIX}${BuildConfig.RETABLE_DRIVER_TABLE_ID}${SUFFIX}"
        const val EMPLOYEES_PATH = "${PREFIX}${BuildConfig.RETABLE_EMPLOYEE_TABLE_ID}${SUFFIX}"
        const val CREATE_OR_UPDATE_EMPLOYEE_PATH = "${BASE_URL_V1}${PREFIX}${BuildConfig.RETABLE_EMPLOYEE_TABLE_ID}${SUFFIX}"
    }
}
