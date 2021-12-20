package com.ouvrirdeveloper.reportbook.worker


import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit


class RemoteConfigWorker(val context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {


    companion object {


        const val TAG = "RemoteConfigWorker"

        fun run(context: Context) {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<RemoteConfigWorker>(24, TimeUnit.HOURS)
                .addTag(TAG)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                TAG,
                ExistingPeriodicWorkPolicy.REPLACE, workRequest
            )
        }
    }

    override suspend fun doWork(): Result {
        return Result.success()
    }


}