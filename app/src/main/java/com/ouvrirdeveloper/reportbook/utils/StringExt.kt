package com.ouvrirdeveloper.reportbook.utils

import java.text.SimpleDateFormat


fun String.formateDate(): String {
//    val spf = SimpleDateFormat("MMM dd, yyyy hh:mm:ss aaa")
    val spf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val tospf = SimpleDateFormat("dd MMM yyyy");
    val date = spf.parse(this);
    return tospf.format(date)
}