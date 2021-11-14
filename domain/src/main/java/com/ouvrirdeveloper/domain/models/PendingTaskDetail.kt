package com.ouvrirdeveloper.domain.models


data class PendingTaskDetail(
    val srNo: Long,
    val docsrchcode: String,
    val doctype: String,
    val docnumber: String,
    val docdate: String,
    val project: String,
    val docvalue: Long,
    val btnview: String,
    val btnapprove: String,
    val btnreject: String
)