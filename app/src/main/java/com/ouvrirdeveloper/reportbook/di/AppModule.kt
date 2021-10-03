package com.ouvrirdeveloper.reportbook.di

import com.ouvrirdeveloper.myreport.ui.splash.SplashViewModel
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.signin.SignInViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { SplashViewModel(get()) }
   
    //viewModel { (valueFromActivity: String) -> HomeViewModel(valueFromActivity) }
}


val CommonModule = module {
    // single { createUnityPlayer(get()) }
}
