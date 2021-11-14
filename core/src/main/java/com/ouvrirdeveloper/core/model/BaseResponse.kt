package com.ouvrirdeveloper.core.model

import com.squareup.moshi.JsonClass

interface Mapper<Y> {
    abstract fun toDomainModel(): Y?
}

@JsonClass(generateAdapter = true)
data class BaseResponse<T>(
    val status: Any?,
    val message: String?,
    val data: T?
)