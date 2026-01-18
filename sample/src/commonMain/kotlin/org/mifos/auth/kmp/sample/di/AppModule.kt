/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.di

import com.russhwolf.settings.Settings
import org.koin.dsl.module
import org.mifos.auth.kmp.core.network.Authenticator
import org.mifos.auth.kmp.library.BasicAuthentication
import org.mifos.auth.kmp.sample.datastore.UserPreferenceDatastore
import org.mifos.auth.kmp.sample.ui.di.UiModule

val AppModule = module {

    single {
        UserPreferenceDatastore(
            get(),
        )
    }
    single {
        Settings()
    }

    includes(UiModule)

    single {
        BasicAuthentication(get())
    }
    single {
        Authenticator()
    }
}
