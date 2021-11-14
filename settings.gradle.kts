dependencyResolutionManagement {
    versionCatalogs {
        
    }
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ReportBook"
include (":app")
include(":domain")
include(":data")
include(":core")
