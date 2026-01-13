package org.mifos.autm.kmp.ui

data class BasicAuthUiConfig(
    val usernameLabel: String = "Username",
    val passwordLabel: String = "Password",
    val loginButtonLabel: String = "Log In",
    val forgotPasswordLabel: String? = "Forgot Password?", // Set to null to hide the button
    val showSignUpLabel: Boolean = true,
    val showPasswordToggle: Boolean = true
)

