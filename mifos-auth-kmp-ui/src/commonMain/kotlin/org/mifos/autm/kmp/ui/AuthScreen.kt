package org.mifos.autm.kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Key
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun AuthScreen(
    state: AuthUiState = AuthUiState(),
    onAction: (AuthAction) -> Unit = {},
    basicAuthUiConfig: BasicAuthUiConfig = BasicAuthUiConfig(),
    style: AuthUiStyle = AuthUiStyle(),
    selectOptionScreenHeader: @Composable BoxScope.() -> Unit = {
        Text("Welcome", style = MaterialTheme.typography.headlineMedium)
    },
    basicAuthScreenHeader: @Composable BoxScope.() -> Unit = {
        Text("Welcome", style = MaterialTheme.typography.headlineMedium)
    },
    oauthScreenHeader: @Composable BoxScope.() -> Unit = {
        Text("Welcome", style = MaterialTheme.typography.headlineMedium)
    },
    errorContent: @Composable ColumnScope.(String) -> Unit = { errorMsg ->
        Text(
            text = errorMsg,
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    },
    footerContent: (@Composable ColumnScope.() -> Unit)? = null
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AuthUiStyle().backgroundColor
    ) {
        if (!state.showBasicAuthForm) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    selectOptionScreenHeader()
                }

                Button(
                    onClick = { },
                    enabled = false,
                    shape = style.buttonShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = style.buttonBackgroundColor,
                        disabledContainerColor = style.buttonDisabledBackgroundColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Key,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text("OAuth")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onAction(AuthAction.BasicAuthButtonClicked) },
                    shape = style.buttonShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = style.buttonBackgroundColor,
                        disabledContainerColor = style.buttonDisabledBackgroundColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(50.dp)
                ) {
                    Text("Basic Auth")
                }

            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {

                BasicAuthScreen(
                    state = state,
                    onAction = onAction,
                    config = basicAuthUiConfig,
                    style = style,
                    headerContent = basicAuthScreenHeader,
                    errorContent = errorContent,
                    footerContent = footerContent
                )

                IconButton(
                    onClick = { onAction(AuthAction.BackButtonClicked) },
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(horizontal = 18.dp, vertical = 32.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}
