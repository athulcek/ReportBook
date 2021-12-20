package com.ouvrirdeveloper.reportbook.features.splash

import android.os.Bundle
import android.os.SystemClock
import androidx.lifecycle.Observer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.ouvrirdeveloper.basearc.core.extension.asString
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.extensions.applogd
import com.ouvrirdeveloper.core.extensions.launchActivity
import com.ouvrirdeveloper.core.extensions.showToast
import com.ouvrirdeveloper.core.utils.permitDiskReads
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.features.home.HomeActivity
import com.ouvrirdeveloper.reportbook.features.signin.SignInActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LandingPageActivity : BaseActivity(R.layout.activity_splash) {
    companion object {
        private const val SPLASH_TIME_OUT = 3000
    }

    private val splashViewModel: SplashViewModel by inject()
    private var lastRetryClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObserver()
        permitDiskReads {
            CoroutineScope(Dispatchers.IO).launch {

                if (splashViewModel.isLoggedIn()) {
                    launchAsPerLoginState()
                } else {
                    launchSignInScreen()
                }
            }
        }
    }


    private fun launchAsPerLoginState() {
        applogd("athul 1 $isNetworkAvailable")
        if (isNetworkAvailable) {
            splashViewModel.doLogin()
        } else {
            showToast(R.string.no_internet_connection.asString(this))
            showRetry(
                R.string.no_internet_connection.asString(this),
                {
                    if ((SystemClock.elapsedRealtime() - lastRetryClickTime) > 1500L) {
                        showProgress()
                        lastRetryClickTime = SystemClock.elapsedRealtime()
                        launchAsPerLoginState()
                    }
                },
                lottieFile = R.raw.no_internet
            )
        }
    }


    private fun launchAndFinishSignInActivity() {
        launchActivity<SignInActivity>()
        finishAfterTransition()
    }


    private fun setupObserver() {
        observeAutoLoginChanges()
    }

    private fun observeAutoLoginChanges() {
        splashViewModel.loginDetail.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideProgress()
//                    observePendingTasks()
                    launchHomeScreen()
                    it.data?.userId?.let {
                        FirebaseCrashlytics.getInstance().setUserId(it)
                    }
                }
                Status.INITIAL,Status.LOADING -> {
                    showProgress(
                        // lottieFile = R.raw.loading,
                        message = R.string.signing_in_progress.asString(this)
                    )
                }
                Status.HTTP_ERROR,
                Status.GENERIC_ERROR -> {
                    launchAndFinishSignInActivity()
                }
                Status.NETWORK_ERROR -> {
                    showRetry(
                        lottieFile = R.raw.no_internet,
                        message = R.string.no_internet_connection.asString(this),
                        reTry = {
                            launchAsPerLoginState()
                        }
                    )
                }
            }
        })
    }

    private fun observePendingTasks() {
        splashViewModel.getPendingTasks()
        splashViewModel.pendingTasks.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    launchHomeScreen()
                }
                Status.GENERIC_ERROR -> launchSignInScreen()
                Status.NETWORK_ERROR -> launchSignInScreen()
                Status.HTTP_ERROR -> launchSignInScreen()
                Status.INITIAL, Status.LOADING -> {
                    showProgress(message = R.string.fetching_pending_tasks.asString(this))
                }
                Status.EMPTY -> launchHomeScreen()
            }

        })
    }

    private fun launchHomeScreen() {
        launchActivity<HomeActivity> { }
        finishAfterTransition()
    }

    private fun launchSignInScreen() {
        launchActivity<SignInActivity>()
        finishAfterTransition()
    }


}