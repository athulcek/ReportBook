object BuildPlugins {
    val buildTools = "com.android.tools.build:gradle:7.0.3"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30"
}

object Plugins {
    val application = "com.android.application"
    val kotlinAndroid = "kotlin-android"
    val kotlin = "kotlin"
    val kotlinxSerialization = "kotlinx-serialization"
    val modulePlugin = "module-plugin"
    val kotlinKapt = "kotlin-kapt"
    val androidLibrary = "com.android.library"
    val kotlinParcelize = "kotlin-parcelize"
    val firebaseCrashlytics = "com.google.firebase.crashlytics"
    val gmsGoogleServices = "com.google.gms.google-services"
    val navigationSafeargsKotlin = "androidx.navigation.safeargs.kotlin"
}


object Deps {

    //Network
    const val MOSHI_CODE_GEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.MOSHI_CONVERTER}"

    //android core
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val ANDROIDX_PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"

    //rooms
    const val ANDROIDX_ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ANDROIDX_ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ANDROIDX_ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

    //viewmodel
    const val VIEW_MODEL_LIFE_CYCLE =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTX_LIFE_CYCLE}"
    const val ANDROIDX_LIFE_CYCLE_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.KTX_LIFE_CYCLE}"

    //layout
    const val ANDROIDX_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"

    //    const val ANDROID_DESIGN = "com.android.support:design:${Versions.DESIGN}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    const val PAGING = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"

    const val ANDROIDX_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    //kotlin
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLIN_COROUTINE_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_CORE}"
    const val COROUTINE_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_CORE}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
    const val KOTLIN_SERIALIZATION =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION}"


    const val FRAGMENT_RUNTIME = "androidx.fragment:fragment:${Versions.ANDROIDX_FRAGMENT}"
    const val FRAGMENT_RUNTIME_KTX = "androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT}"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.SWIPE_REFRESH_LAYOUT}"


    const val NAVIGATION_RUNTIME = "androidx.navigation:navigation-runtime:${Versions.NAVIGATION}"
    const val NAVIGATION_RUNTIME_KTX = "androidx.navigation:navigation-runtime-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment:${Versions.NAVIGATION}"
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui:${Versions.NAVIGATION}"
    const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"


    const val VIEW_PAGER = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER}"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity:${Versions.ANDROIDX_ACTIVITY}"


    const val LOCATION = "com.google.android.gms:play-services-location:${Versions.LOCATION}"

    //play
    const val PLAY_SERVICE_AUTH =
        "com.google.android.gms:play-services-auth:${Versions.PLAY_SERVICE_AUTH}"
    const val PLAY_SERVICE_AUTH_PHONE =
        "com.google.android.gms:play-services-auth-api-phone:${Versions.PLAY_SERVICE_AUTH_PHONE}"

    //firebase

    // Import the BoM for the Firebase platform
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"
    const val FIREBASE_CONFIG_KTX = "com.google.firebase:firebase-config-ktx:${Versions.FIREBASE_CONFIG_KTX}"
//    const val FIREBASE_ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx:${Versions.FIREBASE_BOM}"



    // Declare the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    const val CRASHLYTICS_KTX =
        "com.google.firebase:firebase-crashlytics-ktx:${Versions.CRASHLYTICS_KTX}"
    const val ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx:${Versions.ANALYTICS_KTX}"


    const val WORK_MANAGER = "androidx.work:work-runtime-ktx:${Versions.WORK_MANAGER}"
    const val EXIF_INTERFACE = "androidx.exifinterface:exifinterface:${Versions.EXIF_INTERFACE}"

    //3rd party libraries
    const val OK_HTTP3 = "com.squareup.okhttp3:logging-interceptor:${Versions.OK_HTTP_LOGGING}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"

    const val COIL = "io.coil-kt:coil:${Versions.COIL}"
    const val LIB_PHONE = "io.michaelrocks:libphonenumber-android:${Versions.LIB_PHONE}"
    const val SHIMMER = "com.facebook.shimmer:shimmer:${Versions.SHIMMER}"
    const val KTR = "io.ktor:ktor-client-android:${Versions.KTR}"

    const val SWIPE_HELPER = "com.github.zerobranch:SwipeLayout:${Versions.SWIPE_HELPER}"
    const val TIMBER = "com.jakewharton.timber:timber:${Versions.TIMBER}"

    //koin
    const val KOIN = "io.insert-koin:koin-android:${Versions.KOIN}"
    const val KOIN_CORE = "io.insert-koin:koin-core:${Versions.KOIN}"
    const val KOIN_WORKMANAGER = "io.insert-koin:koin-androidx-workmanager:${Versions.KOIN}"
    const val KOIN_VIEWMODEL = "io.insert-koin:koin-android-viewmodel:${Versions.KOIN}"

    //Test libraries
    const val ANDROIDX_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_UNIT_TEST}"
    const val JUNIT = "junit:junit:4.13.2"

    //debug
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    const val LOGGER = "com.orhanobut:logger:${Versions.LOGGER}"


    const val SAFETYNET = "com.google.android.gms:play-services-safetynet:${Versions.SAFETYNET}"


    const val LOTTIE = "com.airbnb.android:lottie:${Versions.LOTTIE}"

    const val EPOXY = "com.airbnb.android:epoxy:${Versions.EPOXY}"
    const val EPOXY_PROCESSOR = "com.airbnb.android:epoxy-processor:${Versions.EPOXY}"


    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE}"


    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${Versions.COMPOSE_RUNTIME}"
    const val COMPOSE_RUNTIME_LIVEDATA =
        "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE_RUNTIME}"

    // Tooling support (Previews, etc.)
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation:${Versions.COMPOSE}"

    // Material Design
    const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.COMPOSE}"

    // Material design icons
    const val COMPOSE_ICONS = "androidx.compose.material:material-icons-core:${Versions.COMPOSE}"
    const val COMPOSE_ICONS_EXTENTED =
        "androidx.compose.material:material-icons-extended:${Versions.COMPOSE}"

    // Integration with activities
    const val COMPOSE_ACTIVITY = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"

    // Integration with ViewModels
    const val COMPOSE_VIEWMODEL =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.COMPOSE_VIEWMODEL}"

    // Integration with observables
    const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.COMPOSE}"
    const val COMPOSE_ANIMATION = "androidx.compose.animation:animation-core:${Versions.COMPOSE}"


    // UI Tests androidTestImplementation
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"


    const val SHOULDSET = "com.github.MontiniCristian:ShouldSet:${Versions.SHOULDSET}"


}

object BuildTypes {
    val release = "release"
    val debug = "debug"
}

object ProductFlavors {
    const val Production = "production"
    const val Staging = "staging"
    const val Development = "development"
}

object BuildProductDimensions {
    const val Environment = "environment"
}