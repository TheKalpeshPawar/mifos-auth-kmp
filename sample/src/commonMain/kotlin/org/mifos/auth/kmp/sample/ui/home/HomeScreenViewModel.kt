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
    HomeScreenState()
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
    val user: User? = null
)

sealed interface HomeScreenAction {
    object LogoutClicked : HomeScreenAction
}

sealed interface HomeScreenEvent {
    object OnLogout : HomeScreenEvent
}
