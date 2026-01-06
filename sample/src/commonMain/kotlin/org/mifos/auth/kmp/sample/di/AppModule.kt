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
            get()
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