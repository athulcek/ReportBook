package com.ouvrirdeveloper.basearc.ui.base

import androidx.lifecycle.ViewModel
import com.ouvrirdeveloper.core.extensions.apploge
import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean

open class BaseViewModel() : ViewModel() {


    private val viewModelJob = SupervisorJob()
    private var isProgress = AtomicBoolean(false)
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    protected val viewModelIOScope = CoroutineScope(Dispatchers.IO + viewModelJob)


    private val _handler = CoroutineExceptionHandler { _, exception ->
        apploge("CoroutineExceptionHandler got $exception")
    }

    fun runIfNotInProgress(callback: () -> Unit) {
        if (isProgress.get().not()) {
            isProgress = AtomicBoolean(true)
            callback.invoke()
            isProgress = AtomicBoolean(false)
        }
    }

    fun runAsyncTask(callback: suspend () -> Unit): Job {
        val job = viewModelIOScope.launch(_handler) {
            callback.invoke()
        }
        return job
    }

    fun runOnMain(callback: suspend () -> Unit): Job {
        val job = viewModelScope.launch(_handler) {
            callback.invoke()
        }
        return job
    }
}