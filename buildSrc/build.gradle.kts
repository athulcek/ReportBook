import org.gradle.kotlin.dsl.`kotlin-dsl`
plugins {
    `kotlin-dsl`
}

repositories {
    // The org.jetbrains.kotlin.jvm plugin requires a repository
    // where to download the Kotlin compiler dependencies from.
    mavenCentral()
    gradlePluginPortal()
    google()
}

object PluginsVersions {
    const val GRADLE = "4.2.1"
    const val KOTLIN = "1.5.20"
}

dependencies {
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE}")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")
}