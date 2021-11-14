package com.ouvrirdeveloper.data.jobs

import android.content.Context
import androidx.work.*
import com.ouvrirdeveloper.data.jobs.workers.SampleJob
import java.util.concurrent.TimeUnit

class WorkManagerScheduler(private val context: Context) {

    private val sampleJob = "sampleJob"


    fun scheduleJob() {
        val myConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val settingSyncWorkRequest =
            PeriodicWorkRequest.Builder(SampleJob::class.java, 24, TimeUnit.HOURS)
                .setConstraints(myConstraints)
                .setInitialDelay(24, TimeUnit.HOURS)
                .addTag(sampleJob)
                .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            sampleJob,
            ExistingPeriodicWorkPolicy.REPLACE, settingSyncWorkRequest
        )
    }
}