import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {


    val appLibraries = arrayListOf<String>().apply {

        add(Dependencies.KOTLIN_STDLIB)
        add(Dependencies.KOTLIN_REFLECT)
        add(Dependencies.KOTLIN_COROUTINE_CORE)
        add(Dependencies.KOTLIN_COROUTINE_ANDROID)
        add(Dependencies.ANDROIDX_CORE_KTX)
        add(Dependencies.ANDROIDX_APP_COMPAT)
        add(Dependencies.ANDROIDX_CONSTRAINT_LAYOUT)
        add(Dependencies.VIEW_MODEL_LIFE_CYCLE)
        add(Dependencies.ANDROIDX_LIFE_CYCLE_KTX)
        add(Dependencies.ANDROIDX_ACTIVITY)
        add(Dependencies.ANDROIDX_FRAGMENT)
        add(Dependencies.PAGING)
        // add(Dependencies.ANDROID_DESIGN)
        add(Dependencies.FRAGMENT)
        add(Dependencies.NAVIGATION)
        add(Dependencies.VIEW_PAGER)
        add(Dependencies.MATERIAL)
        add(Dependencies.LOCATION)
        add(Dependencies.PLAY_SERVICE_AUTH)
        add(Dependencies.PLAY_SERVICE_AUTH_PHONE)

        add(Dependencies.SWIPE_HELPER)
        add(Dependencies.TIMBER)

        add(Dependencies.EXIF_INTERFACE)

        //koin
        add(Dependencies.KOIN)
        add(Dependencies.KOIN_VIEWMODEL)

        add(Dependencies.COIL)
        add(Dependencies.SHIMMER)
        add(Dependencies.FIREBASE_BOM)
       // add(Dependencies.CRASHLYTICS_KTX)
        add(Dependencies.ANALYTICS_KTX)

        //Coroutines
        add(Dependencies.COROUTINE_ANDROID)




    }

    val debugLibraries = arrayListOf<String>().apply {
        add(Dependencies.LEAK_CANARY)
        add(Dependencies.LOGGER)
    }

    val dataLibraries = arrayListOf<String>().apply {

        add(Dependencies.KOTLIN_STDLIB)
        add(Dependencies.KOTLIN_REFLECT)
        add(Dependencies.KOTLIN_COROUTINE_CORE)
        add(Dependencies.ANDROIDX_CORE_KTX)
        add(Dependencies.ANDROIDX_PREFERENCE)
        add(Dependencies.ANDROIDX_ROOM)
        add(Dependencies.ANDROIDX_ROOM_KTX)
        add(Dependencies.KTR)
        add(Dependencies.TIMBER)
        add(Dependencies.WORK_MANAGER)

        //Retrofit2
        add(Dependencies.RETROFIT)
        add(Dependencies.OK_HTTP3)
        add(Dependencies.MOSHI)
        add(Dependencies.MOSHI_CONVERTER)

        add(Dependencies.LIB_PHONE)
        add(Dependencies.KOIN)


    }

    val dataKapt = arrayListOf<String>().apply {
        add(Dependencies.MOSHI_CODE_GEN)
        add(Dependencies.ANDROIDX_ROOM_COMPILER)

    }

    val domainLibraries = arrayListOf<String>().apply {
        add(Dependencies.KOTLIN_STDLIB)
        add(Dependencies.KOTLIN_REFLECT)
        add(Dependencies.KOTLIN_COROUTINE_CORE)
        add(Dependencies.KOTLIN_SERIALIZATION)
        add(Dependencies.KOIN)
        add(Dependencies.ANDROIDX_CORE_KTX)


    }


    val androidTestLibraries = arrayListOf<String>().apply {
        add(Dependencies.ANDROIDX_JUNIT)
        add(Dependencies.ANDROIDX_ESPRESSO)

    }

    val testLibraries = arrayListOf<String>().apply {
        add(Dependencies.JUNIT)
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.debugImplementation (list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}