import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies




fun Project.addCore() {
    dependencies {
        add("implementation", project(":core"))
    }
}


fun Project.addDomain() {
    dependencies {
        add("implementation", project(":domain"))
    }
}
fun Project.addData() {
    dependencies {
        add("implementation", project(":data"))
    }
}