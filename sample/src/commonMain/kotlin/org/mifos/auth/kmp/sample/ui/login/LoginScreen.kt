package org.mifos.auth.kmp.sample.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.mifos.auth.kmp.sample.Res
import org.mifos.auth.kmp.sample.mifos_logo
import org.mifos.autm.kmp.ui.AuthAction
import org.mifos.autm.kmp.ui.AuthScreen
import org.mifos.autm.kmp.ui.AuthUiStyle


@Composable
fun LoginScreen(
    loginViewModel: LoginScreenViewModel = koinViewModel(),
    onLoginSuccess: () -> Unit,
) {
    val state by loginViewModel.stateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        loginViewModel.eventFlow.collect { event ->
            when (event) {
                LoginScreenEvent.LoginSuccess -> onLoginSuccess()
            }
        }
    }

    if (state.screenState != null && !state.showBasicAuthForm) {
        LoginScreenDialog(
            state = state,
            onDismiss = {
                loginViewModel.trySendAction(LoginScreenAction.DismissError)
            }
        )
    }

    LoginScreenContent(
        state = state,
        onAction = loginViewModel::trySendAction
    )

}

@Composable
fun LoginScreenContent(
    state: LoginScreenState = LoginScreenState(),
    onAction: (LoginScreenAction) -> Unit = {}
) {
    AuthScreen(
        state = state.toBasicAuthUiState().copy(showBasicAuthForm = state.showBasicAuthForm),
        onAction = { authAction ->
            when (authAction) {
                AuthAction.BasicAuthButtonClicked -> onAction(LoginScreenAction.BasicAuthButtonClicked)
                AuthAction.BackButtonClicked -> onAction(LoginScreenAction.BackButtonClicked)
                is AuthAction.UpdatePassword -> onAction(LoginScreenAction.PasswordChanged(authAction.value))
                is AuthAction.UpdateUsername -> onAction(LoginScreenAction.UsernameChanged(authAction.value))
                is AuthAction.LoginClicked -> onAction(
                    LoginScreenAction.LoginButtonClicked(
                        username = state.username,
                        password = state.password
                    )
                )
                AuthAction.TogglePasswordVisibility -> onAction(LoginScreenAction.TogglePasswordVisibility)
            }
        },
        selectOptionScreenHeader = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(Res.drawable.mifos_logo),
                    contentDescription = "Mifos Logo",
                    modifier = Modifier.size(200.dp, 100.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Select sign in method",
                    style = MaterialTheme.typography.titleSmall,
                )

                Spacer(Modifier.height(24.dp))
            }
        },
        basicAuthScreenHeader = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            ) {
                Spacer(Modifier.height(100.dp))
                Image(
                    painter = painterResource(Res.drawable.mifos_logo),
                    contentDescription = "Mifos Logo",
                    modifier = Modifier.size(200.dp, 100.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(Modifier.height(50.dp))
                Text(
                    text = "Enter your login details",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(Modifier.height(16.dp))
            }
        },
        footerContent = {
            MifosPoweredCard(
                textColor = AuthUiStyle().buttonBackgroundColor,
                iconColor = AuthUiStyle().buttonBackgroundColor,
            )
        }
    )
}

@Composable
fun LoginScreenDialog(state: LoginScreenState, onDismiss: () -> Unit) {
    when (state.screenState) {
        is LoginScreenState.ScreenState.Error -> {
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text("Login Failed") },
                text = { Text(state.screenState.message) },
                confirmButton = {
                    TextButton(onClick = onDismiss) {
                        Text("OK")
                    }
                }
            )
        }
        LoginScreenState.ScreenState.Loading -> {
            Dialog(onDismissRequest = {}) {
                Card {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }

        null -> {}
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreenContent()
    }
}
