plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    // id("com.google.gms.google-services")
   // id("com.google.firebase.crashlytics")
}

android {
    compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(AppConfig.BUILD_TOOL_VERSION)
    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/*.kotlin_module")
    }
    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
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
            isShrinkResources = true
            proguardFiles(ProGuards.retrofit)
            proguardFiles(ProGuards.gson)
            proguardFiles(getDefaultProguardFile(ProGuards.proguardTxt), ProGuards.androidDefault)
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"
        }
        getByName("debug") {
            getIsDefault().set(true)
            isMinifyEnabled = false
            manifestPlaceholders["crashlyticsCollectionEnabled"] = "false"
        }
    }

    flavorDimensions(BuildProductDimensions.Environment)
    productFlavors {
        create(ProductFlavors.Development) {
            getIsDefault().set(true)
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project("::core"))
    //app libs
    implementation(AppDependencies.appLibraries)
    //debug libs
    debugImplementation(AppDependencies.debugLibraries)
    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}