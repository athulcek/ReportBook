package plugin

import AppConfig
import BuildConfigFields
import BuildProductDimensions
import BuildTypes
import ProGuards
import ProductFlavors
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class CommonModulePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // apply plugin common to all projects

        // configure the android block
        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            androidExtensions.apply {
                compileSdkVersion(AppConfig.compileSdk)
                buildToolsVersion(AppConfig.buildTool)

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }

                /* val compileKotlin: KotlinCompile by project.tasks
                 compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()*/
                /* project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
                     kotlinOptions {
                         jvmTarget = JavaVersion.VERSION_11.toString()
                         useIR = true
                     }
                 }*/
                testOptions {
                    unitTests.isReturnDefaultValues = true
                }
                packagingOptions {
                    excludes.add("META-INF/DEPENDENCIES")
                    excludes.add("META-INF/*.kotlin_module")
                    excludes.add("META-INF/{AL2.0,LGPL2.1}")
                    resources.excludes += "DebugProbesKt.bin"
                }

                defaultConfig {
                    minSdk = AppConfig.minSdk
                    targetSdk = AppConfig.targetSdk
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    multiDexEnabled = AppConfig.multiDexEnabled
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                    buildConfigField("String", "VERSION_NAME", "\"${AppConfig.versionName}\"")
                    buildConfigField(
                        "int",
                        BuildConfigFields.VERSION_CODE,
                        "${AppConfig.versionCode}"
                    )
                }


                viewBinding.isEnabled = true
                dataBinding.isEnabled = true
                /* buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = Versions.COMPOSE
                    useLiveLiterals =true
                }*/
                when (this) {
                    is LibraryExtension -> {
                        defaultConfig {
                            // apply the pro guard rules for library
                            consumerProguardFiles("consumer-rules.pro")
                        }
                        flavorDimensions(BuildProductDimensions.Environment)
                        productFlavors {
                            create(ProductFlavors.Development) {

                                buildConfigField(
                                    "String",
                                    BuildConfigFields.API_BASE_URL,
                                    "\"http://83.111.85.108/api/\""
                                )
                            }

                            create(ProductFlavors.Staging) {

                                buildConfigField(
                                    "String",
                                    BuildConfigFields.API_BASE_URL,
                                    "\"http://83.111.85.108/api/\""
                                )
                            }

                            create(ProductFlavors.Production) {

                                buildConfigField(
                                    "String",
                                    BuildConfigFields.API_BASE_URL,
                                    "\"http://83.111.85.108/api/\""
                                )
                            }
                        }
                    }

                    is AppExtension -> {

                        buildTypes {
                            getByName(BuildTypes.release) {
                                signingConfig = signingConfigs.create(BuildTypes.release) {
                                    storeFile = File("AppKey.jks")
                                    storePassword = "App#$79845678"
                                    keyAlias = "App"
                                    keyPassword = "App#$79845678"
                                    storeType = "jks"
                                }
                                isMinifyEnabled = true
                                isShrinkResources = true
                                proguardFiles(
                                    ProGuards.retrofit, ProGuards.gson, ProGuards.epoxy,
                                    getDefaultProguardFile(ProGuards.proguardTxt),
                                    ProGuards.androidDefault
                                )
                                manifestPlaceholders["crashlyticsCollectionEnabled"] = "true"

                            }
                            getByName(BuildTypes.debug) {
                                signingConfig = signingConfigs.getByName(BuildTypes.debug) {
                                    storeFile = File("AppKey.jks")
                                    storePassword = "App#$79845678"
                                    keyAlias = "App"
                                    keyPassword = "App#$79845678"
                                    storeType = "jks"
                                }
                                isMinifyEnabled = false
                                isShrinkResources = false
                                isDebuggable = true
                                manifestPlaceholders["crashlyticsCollectionEnabled"] = "false"
                            }
                            flavorDimensions(BuildProductDimensions.Environment)
                            productFlavors {
                                create(ProductFlavors.Development) {
                                    dimension = BuildProductDimensions.Environment
                                    versionNameSuffix = ".dev"
                                    manifestPlaceholders["analyticsCollectionDeactivated"] = "true"
                                    buildConfigField(
                                        "String",
                                        BuildConfigFields.API_BASE_URL,
                                        "\"http://83.111.85.108/api/\""
                                    )
                                }

                                create(ProductFlavors.Staging) {
                                    dimension = BuildProductDimensions.Environment
                                    versionNameSuffix = ".stage"
                                    manifestPlaceholders["analyticsCollectionDeactivated"] = "true"
                                    buildConfigField(
                                        "String",
                                        BuildConfigFields.API_BASE_URL,
                                        "\"http://83.111.85.108/api/\""
                                    )
                                }

                                create(ProductFlavors.Production) {
                                    dimension = BuildProductDimensions.Environment
                                    manifestPlaceholders["analyticsCollectionDeactivated"] = "false"
                                    buildConfigField(
                                        "String",
                                        BuildConfigFields.API_BASE_URL,
                                        "\"http://83.111.85.108/api/\""
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        // dependencies common to all projects
    }
}