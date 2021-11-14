package com.ouvrirdeveloper.domain.helpers.extensions

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass


val json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

inline fun <reified T> String.deserialize(): T {
    return json.decodeFromString(this)
}

inline fun <reified T> T.serialize(): String {
    return Json.encodeToString(this)
}

@ExperimentalSerializationApi
fun <T : Any> String.deserialize(cls: KClass<T>): T? {
    return try {
        json.decodeFromString(Json.serializersModule.serializer(cls.java), this) as T?
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}
