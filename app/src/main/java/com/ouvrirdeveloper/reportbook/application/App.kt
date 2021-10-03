package com.ouvrirdeveloper.reportbook.application

import android.app.Application
import android.os.StrictMode
import androidx.lifecycle.MutableLiveData
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.ouvrirdeveloper.data.di.DataProviderModule
import com.ouvrirdeveloper.data.di.NetworkModule
import com.bushnell.golf.helpers.log.HyperlinkedDebugTree
import com.bushnell.golf.helpers.log.ReleaseTree
import com.orhanobut.logger.*
import com.ouvrirdeveloper.basearc.BuildConfig
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.helper.network.base.ConnectivityProvider
import com.ouvrirdeveloper.core.extensions.AppLog
import com.ouvrirdeveloper.core.log.FileTree
import com.ouvrirdeveloper.domain.di.DomainProviderModule
import com.ouvrirdeveloper.reportbook.di.CommonModule
import com.ouvrirdeveloper.reportbook.di.ViewModelModule
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application(), ImageLoaderFactory, ConnectivityProvider.ConnectivityStateListener {

    companion object {
        lateinit var application: App
    }

    private val provider: ConnectivityProvider by lazy { ConnectivityProvider.createProvider(this) }

    var isNetworkAvailable = MutableLiveData<Boolean>()

    @InternalCoroutinesApi
    override fun onCreate() {
        application = this
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
        super.onCreate()
        startKoin {

            androidContext(this@App)
            modules(
                listOf(
                    ViewModelModule,
                    CommonModule,
                    DataProviderModule,
                    NetworkModule,
                    DomainProviderModule
                )
            )
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(HyperlinkedDebugTree(), FileTree())
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
                .methodCount(0) // (Optional) How many method line to show. Default 2
                .methodOffset(10) // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(LogStrategy { priority, tag, message ->
                    AppLog.log(priority, tag, message)
                }) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(getString(R.string.app_name)) // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()

            Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
           //Logger.addLogAdapter(DiskLogAdapter(formatStrategy))
        } else {
            Timber.plant(ReleaseTree())
        }
        provider.addListener(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
            .allowHardware(false)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
            }
            .build()
    }

    override fun onStateChange(state: ConnectivityProvider.NetworkState) {
        when (state) {
            is ConnectivityProvider.NetworkState.ConnectedState.Connected -> isNetworkAvailable.value =
                true
            is ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy -> isNetworkAvailable.value =
                true
            ConnectivityProvider.NetworkState.NotConnectedState -> isNetworkAvailable.value = false
        }
    }
}