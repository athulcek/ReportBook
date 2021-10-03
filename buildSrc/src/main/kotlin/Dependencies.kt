
object Dependencies {
    //Network
    const val MOSHI_CODE_GEN = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.MOSHI}"
    const val MOSHI = "com.squareup.moshi:moshi:${Versions.MOSHI}"
    const val MOSHI_CONVERTER = "com.squareup.retrofit2:converter-moshi:${Versions.MOSHI_CONVERTER}"

    //android core
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.KTX}"
    const val ANDROIDX_PREFERENCE = "androidx.preference:preference-ktx:${Versions.PREFERENCE}"
   //room
    const val ANDROIDX_ROOM = "androidx.room:room-runtime:${Versions.ROOM}"
    const val ANDROIDX_ROOM_KTX = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ANDROIDX_ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"

    //viewmodel
    const val VIEW_MODEL_LIFE_CYCLE = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.KTX_LIFE_CYCLE}"
    const val ANDROIDX_LIFE_CYCLE_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.KTX_LIFE_CYCLE}"

    //layout
    const val ANDROIDX_CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINT_LAYOUT}"
//    const val ANDROID_DESIGN = "com.android.support:design:${Versions.DESIGN}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    const val PAGING = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"

    const val ANDROIDX_APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    //kotlin
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    const val KOTLIN_COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_CORE}"
    const val KOTLIN_COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_CORE}"
    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_ANDROID}"
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect:${Versions.KOTLIN}"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION}"


    const val NAVIGATION = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"
    const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val VIEW_PAGER = "androidx.viewpager2:viewpager2:${Versions.VIEW_PAGER}"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity:${Versions.ANDROIDX_ACTIVITY}"
    const val ANDROIDX_FRAGMENT = "androidx.fragment:fragment:${Versions.ANDROIDX_FRAGMENT}"



    const val LOCATION = "com.google.android.gms:play-services-location:${Versions.LOCATION}"
    //play
    const val PLAY_SERVICE_AUTH = "com.google.android.gms:play-services-auth:${Versions.PLAY_SERVICE_AUTH}"
    const val PLAY_SERVICE_AUTH_PHONE = "com.google.android.gms:play-services-auth-api-phone:${Versions.PLAY_SERVICE_AUTH_PHONE}"

    //firebase
    const val CRASHLYTICS_KTX = "com.google.firebase:firebase-crashlytics-ktx:${Versions.CRASHLYTICS_KTX}"
    const val ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx:${Versions.ANALYTICS_KTX}"
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:${Versions.FIREBASE_BOM}"

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
    const val KOIN_VIEWMODEL = "org.koin:koin-android-viewmodel:${Versions.KOIN}"
    const val KOIN = "org.koin:koin-android:${Versions.KOIN}"

    //Test libraries
    const val ANDROIDX_ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val ANDROIDX_JUNIT = "androidx.test.ext:junit:${Versions.ANDROIDX_UNIT_TEST}"
    const val JUNIT = "junit:junit:4.13.2"

    //debug
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:${Versions.LEAK_CANARY}"
    const val LOGGER = "com.orhanobut:logger:${Versions.LOGGER}"
}