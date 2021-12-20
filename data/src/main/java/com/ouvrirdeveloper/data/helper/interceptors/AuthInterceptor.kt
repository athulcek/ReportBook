package com.ouvrirdeveloper.data.helper.interceptors

import com.ouvrirdeveloper.data.BuildConfig
import com.ouvrirdeveloper.data.helper.Constants
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val preferenceHelper: PreferenceHelper) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain.request().newBuilder()
        if (chain.request().headers[Constants.HEADER_NO_AUTH] == null) {

        }
        requestBuilder.addHeader(
            Constants.APP_VERSION,
            BuildConfig.VERSION_CODE.toString()
        )
        return chain.proceed(requestBuilder.build())
    }
}