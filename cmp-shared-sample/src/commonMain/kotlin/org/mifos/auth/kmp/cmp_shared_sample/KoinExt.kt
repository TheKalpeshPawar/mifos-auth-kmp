package org.mifos.auth.kmp.cmp_shared_sample

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.mifos.auth.kmp.sample.di.AppModule


fun initKoin(config: KoinAppDeclaration? =null) {
    startKoin {
        config?.invoke(this)
        modules(AppModule)
    }
}