package com.ouvrirdeveloper.reportbook.di

import com.ouvrirdeveloper.reportbook.features.splash.SplashViewModel
import com.ouvrirdeveloper.reportbook.features.home.HomeViewModel
import com.ouvrirdeveloper.reportbook.features.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val ViewModelModule = module {

    viewModel { HomeViewModel(get()) }
    viewModel { SignInViewModel(get(),get()) }
    viewModel { SplashViewModel(get(),get()) }

    //viewModel { (valueFromActivity: String) -> HomeViewModel(valueFromActivity) }
}


val CommonModule = module {
    // single { createUnityPlayer(get()) }
//    single { PrefUtil.init(get()) }
}
