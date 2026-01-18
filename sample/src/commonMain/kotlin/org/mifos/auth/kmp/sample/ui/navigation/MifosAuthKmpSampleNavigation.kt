/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.compose.koinInject
import org.mifos.auth.kmp.sample.datastore.UserPreferenceDatastore
import org.mifos.auth.kmp.sample.ui.home.HomeScreenRoute
import org.mifos.auth.kmp.sample.ui.home.createHomeScreenDestination
import org.mifos.auth.kmp.sample.ui.login.LoginRoute
import org.mifos.auth.kmp.sample.ui.login.createLoginScreenDestination

@Composable
fun MifosAuthKmpSampleNavigation(
    preferenceDatastore: UserPreferenceDatastore = koinInject(),
) {
    val navController = rememberNavController()

    val user = preferenceDatastore.getUser()

    val startDestination = user?.let { u ->
        if (u.isAuthenticated) HomeScreenRoute else LoginRoute
    } ?: LoginRoute

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        createLoginScreenDestination(navController)

        createHomeScreenDestination(navController)
    }
}
