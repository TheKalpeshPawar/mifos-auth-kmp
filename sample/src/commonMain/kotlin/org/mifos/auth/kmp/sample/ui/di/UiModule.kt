/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.ui.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mifos.auth.kmp.sample.ui.home.HomeScreenViewModel
import org.mifos.auth.kmp.sample.ui.login.LoginScreenViewModel

val UiModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
}
