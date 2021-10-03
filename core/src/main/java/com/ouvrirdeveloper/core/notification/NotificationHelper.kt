package com.ouvrirdeveloper.core.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.ouvrirdeveloper.core.BuildConfig
import com.ouvrirdeveloper.core.R

const val NOTIFICATION_CHANNEL_ID = BuildConfig.LIBRARY_PACKAGE_NAME

@Suppress("DEPRECATION")
object NotificationHelper {

    fun buildNotification(
        context: Context,
        pendingIntent: PendingIntent,
        contentText: String,
        text: String = "",
        @DrawableRes
        smallIcon: Int,
        @ColorInt
        color: Int
    ): Notification {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // you will need to provide Channel ID which is new feature in Android Oreo.
            NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setColor(color)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build()
        } else {
            NotificationCompat.Builder(context)
                .setSmallIcon(smallIcon)
                .setContentText(text)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setColor(color)
                .build()
        }
    }

    /**
     * Caution: You should guard this code with a condition on the SDK_INT version to run only on Android 8.0 (API level 26) and
     * higher, because the notification channels APIs are not available in the support library.
     */
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                context.getString(R.string.app_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )

            with(context.getSystemService(NotificationManager::class.java)) {
                createNotificationChannel(serviceChannel)
            }
        }
    }

    fun showNotification(
        context: Context,
        notificationId: Int,
        message: String,
        pendingIntent: PendingIntent,
        autoCancelable: Boolean,
        @DrawableRes
        smallIcon: Int
    ) {
        //Creating an existing notification channel with its original values performs no operation
        createNotificationChannel(context)

        val notificationBuilder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(smallIcon)
            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(message)
            .setAutoCancel(autoCancelable)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(notificationId, notificationBuilder.build())
    }
}