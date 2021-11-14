package com.ouvrirdeveloper.domain.models

import com.ouvrirdeveloper.domain.enums.Gender
import com.ouvrirdeveloper.domain.enums.Status

data class User (
    var userId: String,
    var password: String,
    var companyId: Int,
    val usr_ID_N: Long=0,
    val usr_LoginID_V: String="",
    val usr_Name_V: String="",
    val rol_ID_N: Long=0
)