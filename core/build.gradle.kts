plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(AppConfig.BUILD_TOOL_VERSION)
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/*.kotlin_module")
    }
    defaultConfig {
        minSdkVersion(AppConfig.MIN_SDK_VERSION)
        targetSdkVersion(AppConfig.TARGET_SDK_VERSION)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        buildConfigField("Double", "appVersionName", AppConfig.versionName)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.gson)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
        }
        getByName("debug") {
            isMinifyEnabled = false
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "false"
        }
    }

    flavorDimensions(BuildProductDimensions.Environment)
    productFlavors {
        create(ProductFlavors.Development) {
            dimension = BuildProductDimensions.Environment
            versionNameSuffix = ".dev"
            manifestPlaceholders["analyticsCollectionDeactivated"] = "true"
        }

        create(ProductFlavors.Staging) {
            dimension = BuildProductDimensions.Environment
            versionNameSuffix = ".stage"
            manifestPlaceholders["analyticsCollectionDeactivated"] = "true"

        }

        create(ProductFlavors.Production) {
            dimension = BuildProductDimensions.Environment
            manifestPlaceholders["analyticsCollectionDeactivated"] = "false"

        }
    }
    buildFeatures.viewBinding = true
    buildFeatures.dataBinding = true

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    lintOptions {
        isAbortOnError = false
    }

    packagingOptions {
        doNotStrip("*/armeabi-v7a/*.so")
        doNotStrip("*/arm64-v8a/*.so")
    }
}

dependencies {
    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":domain"))
    //app libs
    implementation(AppDependencies.appLibraries)
    implementation(AppDependencies.dataLibraries)
    kapt(AppDependencies.dataKapt)
    //debug libs
    debugImplementation(AppDependencies.debugLibraries)
    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}