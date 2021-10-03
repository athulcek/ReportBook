package com.ouvrirdeveloper.domain.enums

import com.ouvrirdeveloper.domain.ITypeEnum

enum class Gender(val value: Int, override val type: String) : ITypeEnum<String> {
    Female(0, "female"),
    Male(1, "male");

    companion object {
        public fun fromValue(value: String): Gender = when (value) {
            "female" -> Female
            "male" -> Male
            else -> throw IllegalArgumentException()
        }
    }
}