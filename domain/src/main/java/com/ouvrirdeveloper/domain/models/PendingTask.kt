package com.ouvrirdeveloper.domain.models

data class PendingTask(
    val id:Long,
    val docsrchcode: String,
    val doctype: String,
    val totalcount: Long
)