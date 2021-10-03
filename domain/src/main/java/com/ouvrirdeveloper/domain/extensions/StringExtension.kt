package com.ouvrirdeveloper.domain.helpers.extensions

import java.util.regex.Pattern

fun String?.isNumeric(): Boolean {
    val pattern = Pattern.compile("-?\\d+(\\.\\d+)?")
    if (this == null) {
        return false;
    }
    return pattern.matcher(this).matches();
}