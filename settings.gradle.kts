pluginManagement {
    includeBuild("build-logic")
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
    repositoriesMode = RepositoriesMode.PREFER_PROJECT
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
        maven("https://maven.pkg.jetbrains.space/public/p/koin/dev")
    }
}

rootProject.name = "mifos-auth"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":mifos-auth-kmp")

include(":core:network")
include(":core:data")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":sample")
include(":cmp-android-sample")
include(":cmp-shared-sample")
include(":mifos-auth-kmp-ui")
