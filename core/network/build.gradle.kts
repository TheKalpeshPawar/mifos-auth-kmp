/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.mifos.kmp.library)
}

kotlin {
    android {
        namespace ="org.mifos.auth.kmp.core.network"
    }

    sourceSets {

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.json)

            api(projects.core.common)

        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.koin.android)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        desktopMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }

    }

}
