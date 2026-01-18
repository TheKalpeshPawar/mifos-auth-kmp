/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package cmp.android.sample

import android.app.Application
import cmp.shared.sample.initKoin
import org.koin.android.ext.koin.androidContext

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SampleApp)
        }
    }
}
