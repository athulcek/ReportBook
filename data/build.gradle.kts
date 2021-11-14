plugins {

    id(Plugins.androidLibrary)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroid)
    id(Plugins.modulePlugin)
}

addKoin()
addNetwork()
addCore()
addDomain()
addRoom()
addMoshi()
addAndroidxPreference()
addWork()