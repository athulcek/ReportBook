package com.ouvrirdeveloper.reportbook.appstate

import com.ouvrirdeveloper.core.utils.PrefUtil

class UserState {

    companion object {

        const val PrefUtil_UNAME = "uname"
        const val PrefUtil_PASS_KEY = "pass_key"
        const val PrefUtil_COMPANY_ID = "companyId"
        const val PrefUtil_KEY_EMAIL = "email"
        const val PrefUtil_IS_LOGGED_IN = "is_logged_in"
        const val PrefUtil_KEEP_ME_LOGGED_IN = "keepMeLoggedIn"
    }

    var userName by PrefUtil(PrefUtil_UNAME, "")
    var password by PrefUtil(PrefUtil_PASS_KEY, "")
    var companyId by PrefUtil(PrefUtil_COMPANY_ID, 0)
    var isLoggedIn by PrefUtil(PrefUtil_IS_LOGGED_IN, false)
    var isKeepMeLoggedIn by PrefUtil(PrefUtil_KEEP_ME_LOGGED_IN, false)
    var email by PrefUtil(PrefUtil_KEY_EMAIL, "")


    fun clearData() {
        isLoggedIn = false
        isKeepMeLoggedIn = false
        email = ""
        userName = ""
        password = ""
        companyId = 0
    }
}