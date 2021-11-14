package com.ouvrirdeveloper.domain.enums

import com.ouvrirdeveloper.domain.ITypeEnum

enum class Status(val value: Int, override val type: String) : ITypeEnum<String> {
    Active(0, "active"),
    Inactive(1, "inactive");

    companion object {
        public fun fromValue(value: String): Status = when (value) {
            "active" -> Active
            "inactive" -> Inactive
            else -> throw IllegalArgumentException()
        }
    }
}