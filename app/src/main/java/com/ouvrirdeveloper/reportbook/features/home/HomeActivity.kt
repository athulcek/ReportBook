package com.ouvrirdeveloper.reportbook.features.home

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.ouvrirdeveloper.basearc.core.extension.asColor
import com.ouvrirdeveloper.basearc.core.extension.asDrawable
import com.ouvrirdeveloper.basearc.ui.base.BaseActivityWithBinding
import com.ouvrirdeveloper.core.extensions.applogd
import com.ouvrirdeveloper.core.extensions.launchActivity
import com.ouvrirdeveloper.core.ui.elements.*
import com.ouvrirdeveloper.domain.models.RemoteConfigData
import com.ouvrirdeveloper.reportbook.BuildConfig
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.ActivityHomeBinding
import com.ouvrirdeveloper.reportbook.features.home.pending_task.PendingTaskFragment
import com.ouvrirdeveloper.reportbook.features.splash.SplashScreenActivity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivityWithBinding<ActivityHomeBinding>(R.layout.activity_home) {

    private lateinit var navController: NavController
    private val viewmodel by viewModel<HomeViewModel>()
    val pendingTask = PendingTaskFragment.newInstance()
    val settings = PendingTaskFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppBar(
            title = "Home",
            startIcon = R.drawable.ic_home.asDrawable(this),
            backgroundColorRes = R.color.surface
        )
        networkObserver.observe(this, {
            if (it) {
                binding.viewStatus.setBackgroundColor(R.color.green.asColor(this))
            } else {
                binding.viewStatus.setBackgroundColor(
                    R.color.error.asColor(
                        this
                    )
                )
            }

        })

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setupWithNavController(navController)

        // Setup the ActionBar with navController and 3 top level destinations
        val remoteConfig = Firebase.remoteConfig
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    if (updated) {
                        val enableLogin = remoteConfig.getBoolean("APP_ENABLE_LOGIN")
                        val currentVersion = remoteConfig.getLong("CURRENT_APP_VERSION_CODE")
                        viewmodel.updateConfig(
                            RemoteConfigData(
                                enableLogin,
                                currentVersion.toInt()
                            )
                        )
                        applogd("Config params updated: $updated ,$enableLogin, $currentVersion ")
                    }
                    applogd("Config params updated: $updated ")
                    /*Toast.makeText(
                        this,
                        "Fetch and activate succeeded  $updated, $enable_login ,${currentVersion}",
                        Toast.LENGTH_LONG
                    ).show()*/
                } else {
                    /* Toast.makeText(
                         this, "Fetch failed",
                         Toast.LENGTH_SHORT
                     ).show()*/
                }

            }


    }

    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            viewmodel.verifyCredentialswithRemoteData().collect { config ->
                if (config != null) {
                    applogd("Config params updated ${config.enable_login},${config.currentVersion}")
                    if (config.enable_login.not()) {
                        AwesomeDialog.build(this@HomeActivity)
                            .cancelable(false)
                            .title("UnAuthorised Access")
                            .body("Please contact the Admin")
                            .icon(R.drawable.ic_app)
                            .onPositive("ok") {
                                finishAfterTransition()
                            }
                    } else if (config.currentVersion > BuildConfig.VERSION_CODE) {
                        AwesomeDialog.build(this@HomeActivity)
                            .title("Update")
                            .cancelable(false)
                            .body("Latest version is available please update your app")
                            .icon(R.drawable.ic_app)
                            .onPositive("ok") {

                            }
                            .onNegative("May be Later") {

                            }
                    }


                } else {
                    viewmodel.logout()
                    launchActivity<SplashScreenActivity> { }
                    finish()
                }
            }
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.label == "HomeFragment" || navController.currentDestination?.label == "SettingsFragment") {
            AwesomeDialog.build(this)
                .title("Exit")
                .body("Are you sure to Exit")
                .icon(R.drawable.ic_app)
                .onPositive("Yes") {
                    finishAfterTransition()
                }
                .onNegative("No") {
                }
        } else {
            super.onBackPressed()
        }

    }
    /* override fun onSupportNavigateUp(): Boolean {
         return navController.navigateUp(appBarConfiguration)
     }*/
}