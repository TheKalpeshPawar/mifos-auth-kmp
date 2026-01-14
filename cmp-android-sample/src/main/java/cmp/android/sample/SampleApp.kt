package cmp.android.sample

import android.app.Application
import org.koin.android.ext.koin.androidContext
import cmp.shared.sample.initKoin


class SampleApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@SampleApp)
        }

    }


}
