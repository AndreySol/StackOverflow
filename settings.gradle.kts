pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "StackOverflow"
include(":app")
include(":common")
include(":network")
include(":database")
include(":data")
include(":feature:questions")
include(":feature:answers")
