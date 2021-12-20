package com.ouvrirdeveloper.reportbook.application

import android.app.Application
import android.os.StrictMode
import androidx.lifecycle.MutableLiveData
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.orhanobut.logger.*
import com.ouvrirdeveloper.basearc.helper.network.base.ConnectivityProvider
import com.ouvrirdeveloper.core.di.CoreProviderModule
import com.ouvrirdeveloper.core.extensions.AppLog
import com.ouvrirdeveloper.core.log.HyperlinkedDebugTree
import com.ouvrirdeveloper.core.log.ReleaseTree
import com.ouvrirdeveloper.data.di.DataProviderModule
import com.ouvrirdeveloper.data.di.NetworkModule
import com.ouvrirdeveloper.domain.di.DomainProviderModule
import com.ouvrirdeveloper.reportbook.R
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
        super.onCreate()
        if (com.ouvrirdeveloper.reportbook.BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()

                    .penaltyDropBox()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()

                    .penaltyDropBox()
                    .build()
            )
        }

        startKoin {

            androidContext(this@App)
            modules(
                listOf(
                    ViewModelModule,
                    CommonModule,
                    CoreProviderModule,
                    DataProviderModule,
                    NetworkModule,
                    DomainProviderModule
                )
            )
        }

        if (com.ouvrirdeveloper.reportbook.BuildConfig.DEBUG) {
            Timber.plant(HyperlinkedDebugTree()/*, FileTree()*/)
            val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
                .methodCount(2) // (Optional) How many method line to show. Default 2
                .methodOffset(10) // (Optional) Hides internal method calls up to offset. Default 5
                .logStrategy(LogStrategy { priority, tag, message ->
                    AppLog.log(priority, tag, message)
//                     println("athul $priority, $tag, $message")
                }) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag(getString(R.string.app_name)) // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build()
            val adapter = object : AndroidLogAdapter(formatStrategy) {
                override fun isLoggable(priority: Int, tag: String?): Boolean {
                    return true
                }
            }

            Logger.addLogAdapter(adapter)
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
            is ConnectivityProvider.NetworkState.ConnectedState.Connected,
            is ConnectivityProvider.NetworkState.ConnectedState.ConnectedLegacy -> isNetworkAvailable.value =
                true
            ConnectivityProvider.NetworkState.NotConnectedState -> isNetworkAvailable.value = false
        }
    }
}