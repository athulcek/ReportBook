package com.ouvrirdeveloper.basearc.core.extension

import android.util.Patterns


fun String?.valid(): Boolean =
    this != null && !this.equals("null", true)
            && this.trim().isNotEmpty()

//Email Validation
fun String?.isValidEmail(): Boolean =
    this?.valid() == true && Patterns.EMAIL_ADDRESS.matcher(this).matches()

//Phone number format
/*
fun String.formatPhoneNumber(context: Context, region: String): String? {
    val phoneNumberKit = PhoneNumberUtil.createInstance(context)
    val number = phoneNumberKit.parse(this, region)
    if (!phoneNumberKit.isValidNumber(number))
        return null

    return phoneNumberKit.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
}*/

fun String.toPreservedByteArray(): ByteArray {
    return this.toByteArray(Charsets.ISO_8859_1)
}