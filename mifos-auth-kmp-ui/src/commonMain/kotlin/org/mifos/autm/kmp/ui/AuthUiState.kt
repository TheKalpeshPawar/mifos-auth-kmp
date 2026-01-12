package org.mifos.autm.kmp.ui

data class AuthUiState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isBasicAuthLoginButtonEnabled: Boolean = false,
    val showBasicAuthForm: Boolean = false,
)