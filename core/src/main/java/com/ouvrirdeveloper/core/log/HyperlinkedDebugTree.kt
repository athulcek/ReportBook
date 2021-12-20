package com.ouvrirdeveloper.core.log

import timber.log.Timber

class HyperlinkedDebugTree : Timber.DebugTree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return true
    }


}
