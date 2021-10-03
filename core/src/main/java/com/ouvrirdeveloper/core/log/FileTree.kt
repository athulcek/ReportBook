package com.ouvrirdeveloper.core.log

import android.util.Log
import com.ouvrirdeveloper.core.extensions.applogd
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * The LogElement triple provides an easy wrapper for the Date
 * (as a string), the priority (log level), and the log message.
 */
typealias LogElement = Triple<String, Int, String?>

/**
 * The FileTree is the additional log handler which we plant.
 * It's role is to buffer logs and periodically write them to disk.
 */
@InternalCoroutinesApi
class FileTree : Timber.Tree() {
    private val LOG_LINE_TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    /**
     * The Observable which will receive the log messages which are
     * to be written to disk.
     */
    //private val logBuffer = MutableStateFlow<LogElement>(LogElement("", Log.INFO, ""))
    val logBuffer: Flow<LogElement> = flow<LogElement> {

    }.shareIn(
        CoroutineScope(Dispatchers.IO),
        replay = 1,
        started = SharingStarted.WhileSubscribed()
    )

    init {

        GlobalScope.launch(Dispatchers.IO) {
            logBuffer.collect {
                println("athul ${it.third}")
            }
        }
    }

    /**
     * Schedule this log to be written to disk.
     */
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // For the sake of simplicity we skip logging the exception,
        // but you can parse the exception and and emit it as needed.

         flow<LogElement> {
             emit(
                LogElement(
                    LOG_LINE_TIME_FORMAT.format(Date()),
                    priority,
                    message
                )
            )
        }
    }
}