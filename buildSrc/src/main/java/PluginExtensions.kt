import org.gradle.api.Project


fun Project.addAndroidApplication() {
    plugins.apply(Plugins.application)
    plugins.apply(Plugins.application)
}

fun Project.addKapt() {
    plugins.apply(Plugins.kotlinKapt)
}