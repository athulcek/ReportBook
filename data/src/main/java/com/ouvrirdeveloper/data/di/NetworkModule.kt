package com.ouvrirdeveloper.data.di

import com.ouvrirdeveloper.core.extensions.addValueEnum
import com.ouvrirdeveloper.data.BuildConfig
import com.ouvrirdeveloper.data.api.UserApiService
import com.ouvrirdeveloper.data.helper.PreferenceHelper
import com.ouvrirdeveloper.data.helper.interceptors.AuthInterceptor
import com.ouvrirdeveloper.data.helper.interceptors.LogInterceptor
import com.ouvrirdeveloper.domain.types.DistanceUnits
import com.squareup.moshi.Moshi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val NetworkModule = module {

    single(named("appRetrofit")) { createRetrofit(get(), get(), BuildConfig.API_BASE_URL) }
    single { createOkHttpClient(get(),get()) }
    single { createUserService(get(named("appRetrofit"))) }
    single { createMoshiConverterFactory(get()) }
    single { createMoshi() }
    single { createKtr() }
    single { createLogInterceptor() }

}

fun createOkHttpClient(
    preferenceHelper: PreferenceHelper,
    logInterceptor: LogInterceptor
): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)

    if (BuildConfig.DEBUG) {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(logInterceptor).apply { level = HttpLoggingInterceptor.Level.BODY }
        builder.addInterceptor(httpLoggingInterceptor)
    }
    builder.addInterceptor(AuthInterceptor(preferenceHelper))

    return builder.build()
}

fun createRetrofit(
    okHttpClient: OkHttpClient,
    converter: Converter.Factory,
    url: String
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(converter).build()
}


fun createLogInterceptor() = LogInterceptor()


fun createMoshi(): Moshi {
    return Moshi.Builder()
        .addValueEnum(DistanceUnits::class)
        .build()
}

fun createKtr(): HttpClient {
    return HttpClient(Android)
}

fun createMoshiConverterFactory(moshi: Moshi): Converter.Factory {
    return MoshiConverterFactory.create(moshi)
}


fun createUserService(retrofit: Retrofit): UserApiService =
    retrofit.create(UserApiService::class.java)
