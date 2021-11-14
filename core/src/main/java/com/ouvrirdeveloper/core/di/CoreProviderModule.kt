package com.ouvrirdeveloper.core.di

import com.ouvrirdeveloper.basearc.helper.media.CoilEngine
import com.ouvrirdeveloper.basearc.helper.media.MediaEngine
import org.koin.dsl.module


val CoreProviderModule = module {

    single { createMediaEngine() }

}

fun createMediaEngine(): MediaEngine {
    return CoilEngine()
}
