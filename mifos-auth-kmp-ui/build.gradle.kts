plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.android.lint)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "org.mifos.auth.kmp.ui"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "mifos-auth-kmp-uiKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }


    sourceSets {
        commonMain.dependencies {
            api(projects.mifosAuthKmp)
            api(projects.core.common)

//            implementation(libs.jb.lifecycleViewmodel)
//            implementation(libs.jb.lifecycleViewmodelSavedState)
//            implementation(libs.jb.composeNavigation)
//            implementation(libs.koin.core)
//            implementation(libs.koin.compose)
//            implementation(libs.koin.compose.viewmodel)
//            api(libs.koin.annotations)


            implementation(libs.kotlinx.serialization.json)
//            implementation(libs.ktor.client.core)
//            implementation(libs.ktor.client.json)
//            implementation(libs.ktor.client.logging)
//            implementation(libs.ktor.client.serialization)
//            implementation(libs.ktor.client.content.negotiation)
//            implementation(libs.ktor.client.auth)
//            implementation(libs.ktor.serialization.json)


            // Compose dependencies
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.materialIconsExtended)
        }
        androidMain.dependencies {
//            implementation(libs.ktor.client.okhttp)
//            implementation(libs.koin.android)
        }
        nativeMain.dependencies {
//            implementation(libs.ktor.client.darwin)
        }
        jvmMain.dependencies {
//            implementation(libs.ktor.client.okhttp)
        }
    }


}