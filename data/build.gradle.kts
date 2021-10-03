import BuildConfigFields.API_BASE_URL
import BuildConfigFields.VERSION_CODE
import extensions.buildConfigStringField

plugins {
    id("com.android.library")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileSdkVersion(AppConfig.COMPILE_SDK_VERSION)
    buildToolsVersion(AppConfig.BUILD_TOOL_VERSION)

    defaultConfig {
        minSdkVersion(AppConfig.MIN_SDK_VERSION)
        targetSdkVersion(AppConfig.TARGET_SDK_VERSION)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        buildConfigStringField(VERSION_CODE, AppConfig.versionCode.toString())
    }

    flavorDimensions(BuildProductDimensions.Environment)
    productFlavors {
        create(ProductFlavors.Development) {
            dimension = BuildProductDimensions.Environment
            buildConfigStringField(API_BASE_URL, "http://83.111.85.108/api/")
        }
        create(ProductFlavors.Staging) {
            dimension = BuildProductDimensions.Environment
            buildConfigStringField(API_BASE_URL, "http://83.111.85.108/api/")

        }

        create(ProductFlavors.Production) {
            dimension = BuildProductDimensions.Environment
            buildConfigStringField(API_BASE_URL, "http://83.111.85.108/api/")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard -rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    dependencies {
        implementation(project(":domain"))
        implementation(project(":core"))
        implementation(AppDependencies.dataLibraries)
        kapt(AppDependencies.dataKapt)

    }
}