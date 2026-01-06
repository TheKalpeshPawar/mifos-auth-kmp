package org.mifos.auth.kmp.sample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.mifos.auth.kmp.sample.ui.home.createHomeScreenDestination
import org.mifos.auth.kmp.sample.ui.login.LoginRoute
import org.mifos.auth.kmp.sample.ui.login.createLoginScreenDestination
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.navigation.NavBackStackEntry
import org.koin.compose.koinInject
import org.mifos.auth.kmp.sample.datastore.UserPreferenceDatastore
import org.mifos.auth.kmp.sample.ui.home.HomeScreenRoute

@Composable
fun MifosAuthKmpSampleNavigation(
    preferenceDatastore: UserPreferenceDatastore = koinInject()
){

    val navController = rememberNavController()

    val user = preferenceDatastore.getUser()

    val startDestination = user?.let { u->
        if(u.isAuthenticated) HomeScreenRoute else LoginRoute
    } ?: LoginRoute


    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        createLoginScreenDestination(navController)

        createHomeScreenDestination(navController)

    }

}


