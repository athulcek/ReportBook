package com.ouvrirdeveloper.data.jobs.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent


class SampleJob(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {


    override suspend fun doWork(): Result {

        return Result.success()
    }
}