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
    namespace = "org.mifos.auth.kmp.ui"

}

kotlin {

    sourceSets {
        commonMain.dependencies {
            api(projects.mifosAuthKmp)
            api(projects.core.common)

            implementation(libs.kotlinx.serialization.json)
        }
    }
}