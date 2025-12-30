package org.mifos.auth.kmp.sample.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


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

    if (state.screenState != null) {
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
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Back button in top left corner (only visible in basic auth form)
            if (state.showBasicAuthForm) {
                IconButton(
                    onClick = { onAction(LoginScreenAction.BackButtonClicked) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                if (!state.showBasicAuthForm) {
                    // Show authentication method selection
                    OutlinedButton(
                        onClick = { },
                        enabled = false,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(50.dp)
                            .padding(bottom = 16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Key,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text("OAuth")
                    }

                    OutlinedButton(
                        onClick = { onAction(LoginScreenAction.BasicAuthButtonClicked) },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(50.dp)
                    ) {
                        Text("Basic Auth")
                    }
                } else {
                    // Show basic auth login form
                    OutlinedTextField(
                        value = state.username,
                        onValueChange = { onAction(LoginScreenAction.UsernameChanged(it)) },
                        label = { Text("Username") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { onAction(LoginScreenAction.PasswordChanged(it)) },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp)
                    )

                    Button(
                        onClick = { onAction(LoginScreenAction.LoginButtonClicked(state.username, state.password)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Login")
                    }
                }
            }
        }
    }
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
