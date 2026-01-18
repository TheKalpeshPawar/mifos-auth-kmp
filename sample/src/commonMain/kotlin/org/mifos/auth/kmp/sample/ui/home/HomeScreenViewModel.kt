/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.ui.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mifos.auth.kmp.sample.datastore.UserPreferenceDatastore
import org.mifos.auth.kmp.sample.model.User
import org.mifos.auth.kmp.sample.ui.BaseViewModel

class HomeScreenViewModel(
    private val userPreferenceDatastore: UserPreferenceDatastore,
) : BaseViewModel<HomeScreenState, HomeScreenEvent, HomeScreenAction>(
    HomeScreenState(),
) {

    init {
        loadUser()
    }

    override fun handleAction(action: HomeScreenAction) {
        when (action) {
            HomeScreenAction.LogoutClicked -> {
                handleLogout()
            }
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            val user = userPreferenceDatastore.getUser()
            mutableStateFlow.update {
                it.copy(user = user)
            }
        }
    }

    private fun handleLogout() {
        viewModelScope.launch {
            userPreferenceDatastore.deleteUser()
            mutableStateFlow.update {
                it.copy(user = null)
            }
            sendEvent(HomeScreenEvent.OnLogout)
        }
    }
}

data class HomeScreenState(
    val user: User? = null,
)

sealed interface HomeScreenAction {
    object LogoutClicked : HomeScreenAction
}

sealed interface HomeScreenEvent {
    object OnLogout : HomeScreenEvent
}
