package org.mifos.autm.kmp.ui

interface AuthAction {
    data class UpdateUsername(val value: String) : AuthAction
    data class UpdatePassword(val value: String) : AuthAction
    data object TogglePasswordVisibility : AuthAction
    data object LoginClicked : AuthAction
    data object ForgotPasswordClicked : AuthAction
    data object BasicAuthButtonClicked: AuthAction
    data object BackButtonClicked: AuthAction
}