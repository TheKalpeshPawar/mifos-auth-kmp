package org.mifos.auth.kmp.sample.ui.login

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.mifos.auth.kmp.sample.ui.home.navigateToHomeScreenRoute

@Serializable
data object LoginRoute

fun NavGraphBuilder.createLoginScreenDestination(navController: NavController){
    composable<LoginRoute> {
        LoginScreen(
            onLoginSuccess = {
                navController.navigateToHomeScreenRoute()
            }
        )
    }
}


fun NavController.navigateToLogin(){
    navigate(LoginRoute){
        popUpTo(0)
    }
}