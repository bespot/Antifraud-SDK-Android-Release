pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://artifactory.bespot.com/artifactory/bespot-antifraud")
        maven(url = "https://jitpack.io" )
        maven(url = "https://artifactory.bespot.com/artifactory/bespot-logger")
    }
}

rootProject.name = "Antifraud SDK Android Release"
include(":app")
 