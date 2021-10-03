package com.ouvrirdeveloper.reportbook.features.splash

import android.os.Bundle
import androidx.lifecycle.Observer
import com.ouvrirdeveloper.basearc.R
import com.ouvrirdeveloper.basearc.features.home.HomeActivity
import com.ouvrirdeveloper.basearc.ui.base.BaseActivity
import com.ouvrirdeveloper.core.extensions.launchActivity
import com.ouvrirdeveloper.domain.models.Status
import com.ouvrirdeveloper.myreport.ui.splash.SplashViewModel
import com.ouvrirdeveloper.reportbook.features.base.hideLoading
import com.ouvrirdeveloper.reportbook.features.base.showLoading
import com.ouvrirdeveloper.reportbook.features.base.showRetry
import com.ouvrirdeveloper.reportbook.features.signin.SignInActivity
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
        launchActivity<SignInActivity>()
    }


    private fun launchAsPerLoginState() {
        if (isNetworkAvailable) {
            launchActivity<SignInActivity>()
        } else {
//            showRetry(
//                R.string.no_internet_connection,
//                {
//                    if ((SystemClock.elapsedRealtime() - lastRetryClickTime) > 1500L) {
//                        showLoading(requireBackground = false)
//                        lastRetryClickTime = SystemClock.elapsedRealtime()
//                        launchAsPerLoginState()
//                    }
//                },
//                requireBackground = false
//            )
        }
    }


    private fun launchAndFinishSignInActivity() {
        launchActivity<SignInActivity>()
        finish()
    }


    private fun setupObserver() {

        observeAutoLoginChanges()
    }

    private fun observeAutoLoginChanges() {
        splashViewModel.loginDetail.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    observePendingTasks()
                }
                Status.LOADING -> {
                    showLoading(R.string.signing_in_progress, false)
                }
                Status.HTTP_ERROR,
                Status.GENERIC_ERROR -> {
                    launchAndFinishSignInActivity()
                }
                Status.NETWORK_ERROR -> {
                    showRetry(
                        R.string.no_internet_connection,
                        { },
                        requireBackground = false
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
                    launchActivity<HomeActivity> { }
                }
                Status.GENERIC_ERROR -> launchActivity<SignInActivity>()
                Status.NETWORK_ERROR -> launchActivity<SignInActivity>()
                Status.HTTP_ERROR -> launchActivity<SignInActivity>()
                Status.INITIAL, Status.LOADING -> {
                    showLoading(R.string.fetching_pending_tasks, false)
                }

                Status.EMPTY -> launchActivity<HomeActivity> { }
            }

        })
    }


}