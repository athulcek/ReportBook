package com.ouvrirdeveloper.core.extensions

import android.util.Log
import com.orhanobut.logger.Logger
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.xml.sax.SAXException
import timber.log.Timber
import java.io.IOException
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory

object AppLog {

    fun log(priority: Int, tag: String?, message: String) {
        when (priority) {
            Log.INFO -> Timber.i("$tag $message")
            Log.DEBUG -> Timber.d("$tag $message")
            Log.VERBOSE -> Timber.v("$tag $message")
            Log.ERROR -> Timber.e("$tag $message")
            Log.WARN -> Timber.w("$tag $message")
            Log.ASSERT -> Timber.wtf("$tag $message")

        }

    }

    fun isJSONValid(value: String): Boolean {
        try {
            JSONObject(value)
        } catch (ex: JSONException) {
            try {
                JSONArray(value)
            } catch (ex1: JSONException) {
                return false
            }
        }
        return true
    }

    fun isXMLValid(value: String): Boolean {
        try {
            val dbFactory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance()
            val dBuilder: DocumentBuilder = dbFactory.newDocumentBuilder()
            dBuilder.parse(value)
        } catch (ex: SAXException) {
            return false
        } catch (ex: IOException) {
            return false
        }
        return true
    }
}

fun applogd(log: Any?) {
    when (log) {
        is String -> {
            if (log.isNotEmpty() && AppLog.isJSONValid(log)) {
                Logger.json(log)
            } else if (log.isNotEmpty() && AppLog.isXMLValid(log)) {
                Logger.xml(log)
            } else {
                Logger.d(log)
            }
        }

        else -> {
            Logger.d(log)
        }
    }

}


fun apploge(log: String) = {
    Logger.e(log)
}

fun apploge(exception: Exception) = {
    Logger.e(exception.toString())
}

fun applogw(log: String) = {
    Logger.w(log)
}

fun applogi(log: String) = {
    Logger.i(log)
}





