package com.ouvrirdeveloper.data.helper.interceptors

import com.ouvrirdeveloper.core.extensions.applogd
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer

class LogInterceptor : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        applogd(message)
       /* if (message.startsWith("{") || message.startsWith("[")) {
            try {
                val source: Buffer = Buffer().writeUtf8(message)
                val reader: JsonReader = JsonReader.of(source)
                val value: Any? = reader.readJsonValue()
                val adapter: JsonAdapter<Any> =
                    Moshi.Builder().build().adapter(Any::class.java).indent("    ")
                val result: String = adapter.toJson(value)
                applogd(result)
            } catch (m: Exception) {
                applogd(message)
            }
        } else {
            applogd(message)
        }*/
    }
}