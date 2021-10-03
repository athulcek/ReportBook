package com.ouvrirdeveloper.core.log

import timber.log.Timber


class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

    }

    override fun log(priority: Int, t: Throwable?) {

    }

    override fun log(priority: Int, message: String?, vararg args: Any?) {

    }

    override fun log(priority: Int, t: Throwable?, message: String?, vararg args: Any?) {

    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return false
    }
}
