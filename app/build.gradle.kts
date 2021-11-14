plugins {

    id(Plugins.application)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.navigationSafeargsKotlin)
    id(Plugins.gmsGoogleServices)
    id(Plugins.firebaseCrashlytics)
    id(Plugins.modulePlugin)
}

addCompose()
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
