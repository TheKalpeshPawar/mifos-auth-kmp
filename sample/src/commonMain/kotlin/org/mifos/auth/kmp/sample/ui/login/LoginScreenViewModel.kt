package org.mifos.auth.kmp.sample.ui.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mifos.auth.kmp.core.common.utils.DataState
import org.mifos.auth.kmp.library.BasicAuthentication
import org.mifos.auth.kmp.sample.datastore.UserPreferenceDatastore
import org.mifos.auth.kmp.sample.ui.BaseViewModel
import org.mifos.auth.kmp.sample.util.toUser
import org.mifos.autm.kmp.ui.AuthUiState


class LoginScreenViewModel(
    private val basicAuthentication: BasicAuthentication,
    private val userPreferenceDatastore: UserPreferenceDatastore,
) : BaseViewModel<LoginScreenState, LoginScreenEvent, LoginScreenAction> (
    LoginScreenState()
) {

    override fun handleAction(action: LoginScreenAction) {
        when(action) {
            is LoginScreenAction.LoginButtonClicked -> {
                handleLoginButtonClicked(
                    action.username,
                    action.password
                )
            }
            is LoginScreenAction.PasswordChanged -> {
                mutableStateFlow.update {
                    it.copy(password = action.password)
                }
            }
            is LoginScreenAction.UsernameChanged -> {
                mutableStateFlow.update {
                    it.copy(username = action.username)
                }
            }
            LoginScreenAction.DismissError -> {
                mutableStateFlow.update {
                    it.copy(
                        screenState = null,
                        password = "",
                        username = ""
                    )
                }
            }
            LoginScreenAction.BasicAuthButtonClicked -> {
                mutableStateFlow.update {
                    it.copy(showBasicAuthForm = true)
                }
            }
            LoginScreenAction.BackButtonClicked -> {
                mutableStateFlow.update {
                    it.copy(
                        showBasicAuthForm = false,
                        username = "",
                        password = ""
                    )
                }
            }
            LoginScreenAction.TogglePasswordVisibility -> {
                mutableStateFlow.update {
                    it.copy(isPasswordVisible = !it.isPasswordVisible)
                }
            }
        }

    }


    private fun handleLoginButtonClicked(username: String, password: String) {
        viewModelScope.launch {
            basicAuthentication(
                username,
                password,
                queryParameters = {
                    append("returnClientList", "true")
                }
            ).collect { dataState ->

                when (dataState) {
                    is DataState.Error -> {
                        mutableStateFlow.update {
                            it.copy(
                                screenState = LoginScreenState.ScreenState.Error(dataState.message)
                            )
                        }
                    }

                    DataState.Loading -> {
                        mutableStateFlow.update {
                            it.copy(
                                screenState = LoginScreenState.ScreenState.Loading
                            )
                        }
                    }

                    is DataState.Success -> {
                        mutableStateFlow.update {
                            it.copy(
                                screenState = null
                            )
                        }
                        try {
                            userPreferenceDatastore.saveUser(
                                dataState.data.toUser(true)
                            )
                            sendEvent(LoginScreenEvent.LoginSuccess)
                        } catch (e: Exception) {
                            mutableStateFlow.update {
                                it.copy(
                                    screenState = LoginScreenState.ScreenState.Error(e.message ?: "Unknown Error")
                                )
                            }
                        }
                    }
                }

            }
        }

    }


}

fun LoginScreenState.toBasicAuthUiState(): AuthUiState {
    return AuthUiState(
        username = username,
        password = password,
        isPasswordVisible = isPasswordVisible,
        isLoading = screenState is LoginScreenState.ScreenState.Loading,
        errorMessage = (screenState as? LoginScreenState.ScreenState.Error)?.message,
        isBasicAuthLoginButtonEnabled = username.isNotBlank() && password.isNotBlank()
    )
}


data class LoginScreenState(
    val password: String = "",
    val username: String = "",
    val screenState: ScreenState? = null,
    val showBasicAuthForm: Boolean = false,
    val isPasswordVisible: Boolean = false
) {
    sealed interface ScreenState {
        object Loading : ScreenState
        data class Error(val message: String): ScreenState
    }
}


sealed interface LoginScreenAction {
    data class UsernameChanged(val username: String) : LoginScreenAction
    data class PasswordChanged(val password: String) : LoginScreenAction
    data class LoginButtonClicked(val username: String, val password: String) : LoginScreenAction
    object DismissError : LoginScreenAction
    object BasicAuthButtonClicked : LoginScreenAction
    object BackButtonClicked : LoginScreenAction
    object TogglePasswordVisibility : LoginScreenAction
}

sealed interface LoginScreenEvent {
    object LoginSuccess : LoginScreenEvent
}
