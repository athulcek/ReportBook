package com.bushnell.golf.helpers.log

import timber.log.Timber

class HyperlinkedDebugTree : Timber.DebugTree() {
    /*override fun createStackElementTag(element: StackTraceElement): String? {
        with(element) {
            return "AppLog -> ($fileName:$lineNumber) $methodName()"
        }
    }*/

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return true
    }


}
