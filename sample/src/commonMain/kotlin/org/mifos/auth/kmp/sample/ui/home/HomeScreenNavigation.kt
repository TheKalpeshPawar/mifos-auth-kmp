package org.mifos.auth.kmp.sample.ui.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.mifos.auth.kmp.sample.ui.login.navigateToLogin


@Serializable
data object HomeScreenRoute

fun NavGraphBuilder.createHomeScreenDestination(navController: NavController){
    composable<HomeScreenRoute> {
        HomeScreen {
            navController.navigateToLogin()
        }
    }
}

fun NavController.navigateToHomeScreenRoute() {
    navigate(HomeScreenRoute) {
        popUpTo(0)
    }
}