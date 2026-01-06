package org.mifos.auth.kmp.cmp_android_sample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.mifos.auth.kmp.cmp_shared_sample.initKoin


class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SampleApp)
        }

    }


}