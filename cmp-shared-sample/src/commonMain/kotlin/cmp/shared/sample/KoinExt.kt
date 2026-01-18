/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package cmp.shared.sample

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.mifos.auth.kmp.sample.di.AppModule

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(AppModule)
    }
}
