pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/koin/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
