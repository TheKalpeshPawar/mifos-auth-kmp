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
    namespace = "cmp.shared.sample"
}


kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)

                api(projects.sample)
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
            }
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.desktop)
        }

    }

}
