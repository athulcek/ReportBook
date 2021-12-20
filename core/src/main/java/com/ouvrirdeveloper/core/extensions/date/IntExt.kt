package com.ouvrirdeveloper.core.extensions.date


import java.util.Calendar

fun Int.toYear() = Duration(unit = Calendar.YEAR, value = this)

fun Int.toMonth() = Duration(unit = Calendar.MONTH, value = this)

fun Int.toWeek() = Duration(unit = Calendar.WEEK_OF_MONTH, value = this)

fun Int.toDay() = Duration(unit = Calendar.DATE, value = this)

fun Int.toHour() = Duration(unit = Calendar.HOUR_OF_DAY, value = this)

fun Int.toMinute() = Duration(unit = Calendar.MINUTE, value = this)

fun Int.toSecond() = Duration(unit = Calendar.SECOND, value = this)

fun Int.toMillisecond() = Duration(unit = Calendar.MILLISECOND, value = this)