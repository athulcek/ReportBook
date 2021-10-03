package com.ouvrirdeveloper.data

import com.ouvrirdeveloper.core.extensions.apploge
import com.ouvrirdeveloper.data.models.responses.ErrorResponse
import com.ouvrirdeveloper.domain.models.Resource
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
    return try {
        val result = Resource.success(apiCall.invoke())
        result
    } catch (throwable: Throwable) {
        apploge("Error Thrown error in Api Call " + throwable.message)

        when (throwable) {
            is IOException -> Resource.networkError(throwable.message ?: "Network error", null)
            is SocketTimeoutException -> Resource.networkError(
                throwable.message ?: "Network error",
                null
            )
            is HttpException -> {
                val errorBody = convertErrorBody(throwable)
                Resource.httpError(
                    errorBody?.status ?: throwable.code(),
                    errorBody?.title ?: "",
                    null
                )
            }
            else -> Resource.genericError(throwable.message ?: "Unknown error occurred", null)
        }
    }
}

private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}