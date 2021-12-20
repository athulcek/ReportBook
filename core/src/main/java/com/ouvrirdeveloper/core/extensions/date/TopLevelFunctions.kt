package com.ouvrirdeveloper.core.extensions.date


import java.util.Calendar
import java.util.Date

internal val calendar: Calendar by lazy {
    Calendar.getInstance()
}

fun now() = Date()

fun today() = Date()

fun yesterday() = today() - 1.toDay()

fun currentMonthRange(): DateRange = today().getMonthRange()

fun previousMonthRange(): DateRange = (today() - 1.toMonth()).getMonthRange()

fun nextMonthRange() = (today() + 1.toMonth()).getMonthRange()

fun currentYearRange(): DateRange = today().getYearRange()

fun previousYearRange(): DateRange = (today() - 1.toYear()).getYearRange()

fun nextYearRange(): DateRange = (today() + 1.toYear()).getYearRange()