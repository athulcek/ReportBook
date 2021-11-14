package com.ouvrirdeveloper.domain.types

import com.ouvrirdeveloper.domain.ITypeEnum


enum class DistanceUnits(override val type: Int) : ITypeEnum<Int> {
    Yards(1),
    Meters(2);

    companion object {
        fun fromInt(value: Int): DistanceUnits? =
            values().find { it.type == value }
    }
}