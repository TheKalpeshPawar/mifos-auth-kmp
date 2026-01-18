/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */

plugins {
    alias(libs.plugins.mifos.cmp.feature)
}


android {
    namespace = "org.mifos.auth.kmp.sample"
}

kotlin {

    sourceSets {
        commonMain.dependencies {
            api(projects.mifosAuthKmp)
            api(projects.mifosAuthKmpUi)
            api(projects.core.common)


            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.annotations)


            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.serialization.json)

            implementation(libs.multiplatform.settings)
            implementation(libs.multiplatform.settings.serialization)
            implementation(libs.multiplatform.settings.coroutines)

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

compose.resources {
    packageOfResClass = "org.mifos.auth.kmp.sample"
}

dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}
