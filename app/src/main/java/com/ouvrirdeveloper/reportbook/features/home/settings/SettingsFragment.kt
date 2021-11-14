package com.ouvrirdeveloper.reportbook.features.home.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import com.ouvrirdeveloper.reportbook.R
import com.ouvrirdeveloper.reportbook.databinding.FragmentSettingsBinding
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import org.koin.android.ext.android.inject


class SettingsFragment :
    BaseFragmentWithBinding<FragmentSettingsBinding>(R.layout.fragment_settings) {

    private val viewModel: HomeViewModel by inject()

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchDayNight.setOnClickListener {
            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme) {
                Configuration.UI_MODE_NIGHT_YES ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }


}