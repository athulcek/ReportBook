plugins {
    id(Plugins.kotlin)
    kotlin("plugin.serialization") version Versions.KOTLIN

}
repositories {
    mavenCentral()
}

dependencies {
    implementation(Deps.KOIN_CORE)
    implementation(Deps.KOTLIN_STDLIB)
    implementation(Deps.KOTLIN_REFLECT)
    implementation(Deps.KOTLIN_COROUTINE_CORE)
    implementation(Deps.KOTLIN_SERIALIZATION)

}