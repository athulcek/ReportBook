package com.ouvrirdeveloper.reportbook.features.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ouvrirdeveloper.core.extensions.launchActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchActivity<LandingPageActivity>()
        finish()
    }


}