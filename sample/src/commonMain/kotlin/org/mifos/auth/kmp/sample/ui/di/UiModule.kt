package org.mifos.auth.kmp.sample.ui.di

import androidx.compose.ui.input.key.Key.Companion.Home
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.mifos.auth.kmp.sample.ui.home.HomeScreenViewModel
import org.mifos.auth.kmp.sample.ui.login.LoginScreenViewModel

val UiModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
}