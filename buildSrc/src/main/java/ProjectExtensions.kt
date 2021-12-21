import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


fun Project.addKoin() {
    dependencies {
        add("implementation", Deps.KOIN)
    }
}

fun Project.addNetwork() {
    dependencies {
        add("implementation", Deps.OK_HTTP3)
        add("implementation", Deps.RETROFIT)
        add("implementation", Deps.KTR)
    }
}

fun Project.addCoil() {
    dependencies {
        add("implementation", Deps.COIL)
    }
}

fun Project.addAndroidxPreference() {
    dependencies {
        add("implementation", Deps.ANDROIDX_PREFERENCE)
    }
}

fun Project.addMoshi() {
    dependencies {
        add("implementation", Deps.MOSHI)
        add("kapt", Deps.MOSHI_CODE_GEN)
        add("implementation", Deps.MOSHI_CONVERTER)
    }
}

fun Project.addWork() {
    dependencies {
        add("implementation", Deps.WORK_MANAGER)
        add("implementation", Deps.KOIN_WORKMANAGER)
    }
}

fun Project.addExifinterface() {
    dependencies {
        add("implementation", Deps.EXIF_INTERFACE)
    }
}

fun Project.addCrashlytics() {
    dependencies {
        add("implementation", Deps.FIREBASE_BOM)
        add("implementation", Deps.CRASHLYTICS_KTX)
        add("implementation", Deps.ANALYTICS_KTX)
    }
}
fun Project.addRemoteConfig() {
    dependencies {
        add("implementation", Deps.FIREBASE_BOM)
        add("implementation", Deps.FIREBASE_CONFIG_KTX)

    }
}

fun Project.addKotlin() {
    dependencies {
        add("implementation", Deps.KOTLIN_STDLIB)
        add("implementation", Deps.KOTLIN_COROUTINE_CORE)
        add("implementation", Deps.COROUTINE_ANDROID)
        add("implementation", Deps.KOTLIN_REFLECT)
        add("implementation", Deps.KOTLIN_SERIALIZATION)
    }
}

fun Project.addAndroid() {
    dependencies {
        add("implementation", Deps.ANDROIDX_CORE_KTX)
        add("implementation", Deps.ANDROIDX_APP_COMPAT)
        add("implementation", Deps.ANDROIDX_LIFE_CYCLE_KTX)
        add("implementation", Deps.VIEW_MODEL_LIFE_CYCLE)
        add("implementation", Deps.ANDROIDX_CONSTRAINT_LAYOUT)
        add("implementation", Deps.VIEW_PAGER)
        add("implementation", Deps.SWIPE_REFRESH_LAYOUT)
        //add("implementation", Deps.ABOUTLIBRARIES)
        //add("implementation", Deps.ABOUTLIBRARIES_LEGACY)
//        add("implementation", Deps.SHOULDSET)
        //  add("implementation", Deps.KOIN_VIEWMODEL)
    }
    addNavigation()
    addFragment()
    addActivity()
    addPaging()
    addAndroidxPreference()
}


fun Project.addTimber() {
    dependencies {
        add("implementation", Deps.TIMBER)
    }
}

fun Project.addPaging() {
    dependencies {
        add("implementation", Deps.PAGING)
    }
}

fun Project.addFragment() {
    dependencies {
        add("implementation", Deps.FRAGMENT_RUNTIME)
        add("implementation", Deps.FRAGMENT_RUNTIME_KTX)
    }
}

fun Project.addActivity() {
    dependencies {
        add("implementation", Deps.ANDROIDX_ACTIVITY)
    }
}

fun Project.addNavigation() {
    dependencies {
        add("implementation", Deps.NAVIGATION_RUNTIME)
        add("implementation", Deps.NAVIGATION_RUNTIME_KTX)
        add("implementation", Deps.NAVIGATION_FRAGMENT)
        add("implementation", Deps.NAVIGATION_FRAGMENT_KTX)
        add("implementation", Deps.NAVIGATION_UI)
        add("implementation", Deps.NAVIGATION_UI_KTX)
    }
}

fun Project.addMaterial() {
    dependencies {
        add("implementation", Deps.MATERIAL)
    }
}

fun Project.addRoom() {
    dependencies {
        add("implementation", Deps.ANDROIDX_ROOM)
        add("implementation", Deps.ANDROIDX_ROOM_KTX)
        add("kapt", Deps.ANDROIDX_ROOM_COMPILER)
    }
}

fun Project.addTest() {
    dependencies {
        add("androidTestImplementation", Deps.ANDROIDX_ESPRESSO)
        add("androidTestImplementation", Deps.ANDROIDX_JUNIT)
        add("testImplementation", Deps.JUNIT)
    }
}

fun Project.addDebug() {
    dependencies {
        add("debugImplementation", Deps.LEAK_CANARY)

    }
    addTimber()
    addLogger()
}

fun Project.addLogger() {
    dependencies {
        add("implementation", Deps.LOGGER)
    }
    addTimber()
}

fun Project.addSecurity() {
    dependencies {
        add("implementation", Deps.SAFETYNET)
    }
}

fun Project.addLottie() {
    dependencies {
        add("implementation", Deps.LOTTIE)
    }
}

fun Project.addEpoxyRecyclerView() {
    dependencies {
        add("implementation", Deps.EPOXY)
        add("kapt", Deps.EPOXY_PROCESSOR)
    }
}

fun Project.addCompose() {
    dependencies {
        //add("implementation", Deps.COMPOSE_RUNTIME)
        add("implementation", Deps.COMPOSE_UI)
        add("implementation", Deps.COMPOSE_ANIMATION)
        add("implementation", Deps.COMPOSE_TOOLING)
        add("implementation", Deps.COMPOSE_FOUNDATION)
        add("implementation", Deps.COMPOSE_MATERIAL)
        add("implementation", Deps.COMPOSE_ICONS)
        add("implementation", Deps.COMPOSE_ICONS_EXTENTED)
        add("implementation", Deps.COMPOSE_ACTIVITY)
        add("implementation", Deps.COMPOSE_VIEWMODEL)
        add("implementation", Deps.COMPOSE_LIVEDATA)
        add("androidTestImplementation", Deps.COMPOSE_JUNIT)
    }
}