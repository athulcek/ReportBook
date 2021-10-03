package com.ouvrirdeveloper.data.jobs.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent

@KoinApiExtension
class SampleJob(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams), KoinComponent {


    override suspend fun doWork(): Result {

        return Result.success()
    }
}