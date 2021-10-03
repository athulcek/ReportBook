package com.ouvrirdeveloper.domain.usecases

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicBoolean

open class BaseUseCase {

    private val viewModelJob = SupervisorJob()
    private var isProgress = AtomicBoolean(false)
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    protected val viewModelIOScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _handler = CoroutineExceptionHandler { _, exception ->
        //("CoroutineExceptionHandler got $exception")
    }
    fun runAsyncTask(callback: suspend () -> Unit): Job {
        val job = viewModelIOScope.launch(_handler) {
            callback.invoke()
        }
        return job
    }
}