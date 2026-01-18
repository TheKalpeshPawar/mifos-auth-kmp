/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.autm.kmp.ui

interface AuthAction {
    data class UpdateUsername(val value: String) : AuthAction
    data class UpdatePassword(val value: String) : AuthAction
    data object TogglePasswordVisibility : AuthAction
    data object LoginClicked : AuthAction
    data object ForgotPasswordClicked : AuthAction
    data object BasicAuthButtonClicked : AuthAction
    data object BackButtonClicked : AuthAction
}
