package com.ouvrirdeveloper.core.extensions

fun ByteArray.toPreservedString(): String {
    return String(this, Charsets.ISO_8859_1)
}