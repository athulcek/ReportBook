import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.application)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeargsKotlin)
    id(Plugins.gmsGoogleServices)
    id(Plugins.firebaseCrashlytics)
    id(Plugins.modulePlugin)
   // id(Plugins.aboutlibraries) version Versions.ABOUTLIBRARIES
}

kapt {
    generateStubs =true
    correctErrorTypes = true
}
android {
    defaultConfig {
        applicationId = AppConfig.applicationId
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    kapt {
        generateStubs =true
        correctErrorTypes = true
    }
    /* composeOptions {
         kotlinCompilerExtensionVersion = Versions.COMPOSE
     }*/
}

//addCompose()
addKoin()
addKotlin()
addAndroid()
addMaterial()
addDebug()
addCore()
addCoil()
addDomain()
addData()
addLottie()
addEpoxyRecyclerView()
addCrashlytics()
addRemoteConfig()

