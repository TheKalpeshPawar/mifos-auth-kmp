/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.autm.kmp.ui

data class BasicAuthUiConfig(
    val usernameLabel: String = "Username",
    val passwordLabel: String = "Password",
    val loginButtonLabel: String = "Log In",
    /*
     * Set to null to hide the button
     * */
    val forgotPasswordLabel: String? = "Forgot Password?",
    val showSignUpLabel: Boolean = true,
    val showPasswordToggle: Boolean = true,
)
