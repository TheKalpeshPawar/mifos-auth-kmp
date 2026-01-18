/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.mifos.auth.kmp.sample.ui.home.navigateToHomeScreenRoute

@Serializable
data object LoginRoute

fun NavGraphBuilder.createLoginScreenDestination(navController: NavController) {
    composable<LoginRoute> {
        LoginScreen(
            onLoginSuccess = {
                navController.navigateToHomeScreenRoute()
            },
        )
    }
}

fun NavController.navigateToLogin() {
    navigate(LoginRoute) {
        popUpTo(0)
    }
}
