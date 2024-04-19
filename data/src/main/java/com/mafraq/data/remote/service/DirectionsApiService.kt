package com.mafraq.data.remote.service

import com.mafraq.data.BuildConfig
import com.mafraq.data.remote.models.routes.request.RouteDirectionsBody
import com.mafraq.data.remote.models.routes.response.DirectionsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface DirectionsApiService {
    @Headers(
        ACCEPT_HEADER,
        ACCEPT_LANGUAGE_HEADER,
        CONTENT_TYPE_HEADER,
        ORIGIN_HEADER,
        REFERER_HEADER,
        SEC_CH_UA_HEADER,
        SEC_CH_UA_MOBILE_HEADER,
        SEC_CH_UA_PLATFORM_HEADER,
        SEC_FETCH_DEST_HEADER,
        SEC_FETCH_MODE_HEADER,
        SEC_FETCH_SITE_HEADER,
        USER_AGENT_HEADER,
        X_GOOG_API_KEY_HEADER,
        X_GOOG_FIELDMASK_HEADER
    )
    @POST("directions/v2:computeRoutes")
    suspend fun computeRoutes(@Body body: RouteDirectionsBody): Response<DirectionsResponse>

    private companion object {
        const val ACCEPT_HEADER = "accept: */*"
        const val ACCEPT_LANGUAGE_HEADER = "accept-language: en,ar;q=0.9,en-US;q=0.8,fa;q=0.7"
        const val CONTENT_TYPE_HEADER = "content-type: application/json"
        const val ORIGIN_HEADER = "origin: https://developers-dot-devsite-v2-prod.appspot.com"
        const val REFERER_HEADER = "referer: https://developers-dot-devsite-v2-prod.appspot.com/"
        const val SEC_CH_UA_HEADER =
            "sec-ch-ua: \"Microsoft Edge\";v=\"123\", \"Not:A-Brand\";v=\"8\", \"Chromium\";v=\"123\""
        const val SEC_CH_UA_MOBILE_HEADER = "sec-ch-ua-mobile: ?0"
        const val SEC_CH_UA_PLATFORM_HEADER = "sec-ch-ua-platform: \"macOS\""
        const val SEC_FETCH_DEST_HEADER = "sec-fetch-dest: empty"
        const val SEC_FETCH_MODE_HEADER = "sec-fetch-mode: cors"
        const val SEC_FETCH_SITE_HEADER = "sec-fetch-site: cross-site"
        const val USER_AGENT_HEADER =
            "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36 Edg/123.0.0.0"
        const val X_GOOG_API_KEY_HEADER = "x-goog-api-key: ${BuildConfig.ROUTES_API_KEY}"
        const val X_GOOG_FIELDMASK_HEADER = "x-goog-fieldmask: *"
    }
}
