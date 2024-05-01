package com.mafraq.data.remote.service

import com.mafraq.data.BuildConfig
import com.mafraq.data.remote.models.OpenMapResponseRemote
import com.mafraq.data.remote.models.routes.request.RouteDirectionsBody
import com.mafraq.data.remote.models.routes.response.DirectionsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface DirectionsApiService {
    @Headers(
        ACCEPT_HEADER,
        ACCEPT_LANGUAGE_HEADER,
        CONTENT_TYPE_HEADER,
        ORIGIN_HEADER,
        REFERER_HEADER,
        SEC_CH_UA_HEADER,
        SEC_CH_UA_MOBILE_HEADER,
        SEC_FETCH_DEST_HEADER,
        SEC_FETCH_MODE_HEADER,
        SEC_FETCH_SITE_HEADER,
        X_GOOG_API_KEY_HEADER,
        X_GOOG_FIELDMASK_HEADER
    )
    @POST("directions/v2:computeRoutes")
    suspend fun computeRoutes(@Body body: RouteDirectionsBody): Response<DirectionsResponse>

    @GET(LOCATION_INFO_PATH)
    suspend fun getLocationInfo(
        @Query("format")
        format: String = "json",
        @Query("apiKey")
        apiKey: String = BuildConfig.GEOCODING_API_KEY,
        @Query("lat")
        lat: Double,
        @Query("lon")
        lng: Double,
    ): Response<OpenMapResponseRemote>

    private companion object {
        private const val GEO_API_BASE_URL = "https://api.geoapify.com/"
        const val LOCATION_INFO_PATH = GEO_API_BASE_URL + "v1/geocode/reverse"
        const val ACCEPT_HEADER = "accept: */*"
        const val ACCEPT_LANGUAGE_HEADER = "accept-language: en,ar;q=0.9,en-US;q=0.8,fa;q=0.7"
        const val CONTENT_TYPE_HEADER = "content-type: application/json"
        const val ORIGIN_HEADER = "origin: https://developers-dot-devsite-v2-prod.appspot.com"
        const val REFERER_HEADER = "referer: https://developers-dot-devsite-v2-prod.appspot.com/"
        const val SEC_CH_UA_HEADER =
            "sec-ch-ua: \"Microsoft Edge\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";v=\"123\""
        const val SEC_CH_UA_MOBILE_HEADER = "sec-ch-ua-mobile: ?0"
        const val SEC_FETCH_DEST_HEADER = "sec-fetch-dest: empty"
        const val SEC_FETCH_MODE_HEADER = "sec-fetch-mode: cors"
        const val SEC_FETCH_SITE_HEADER = "sec-fetch-site: cross-site"
         const val X_GOOG_API_KEY_HEADER = "x-goog-api-key: ${BuildConfig.ROUTES_API_KEY}"
        const val X_GOOG_FIELDMASK_HEADER = "x-goog-fieldmask: *"
    }
}
