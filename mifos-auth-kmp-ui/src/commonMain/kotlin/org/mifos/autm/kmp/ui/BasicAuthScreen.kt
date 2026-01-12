package org.mifos.autm.kmp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun BasicAuthScreen(
    state: AuthUiState,
    onAction: (AuthAction) -> Unit,
    config: BasicAuthUiConfig = BasicAuthUiConfig(),
    style: AuthUiStyle = AuthUiStyle(),
    headerContent: @Composable ColumnScope.() -> Unit = {
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
    Column(
        modifier = style.containerModifier
            .fillMaxSize()
            .background(style.backgroundColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        headerContent()

        if (state.errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            errorContent(state.errorMessage)
        }

        Spacer(modifier = Modifier.height(style.spacingAfterHeader))

        OutlinedTextField(
            value = state.username,
            onValueChange = { onAction(AuthAction.UpdateUsername(it)) },
            placeholder = {
                Text(
                    text = config.usernameLabel,
                    style = style.textFieldTextStyle,
                    color = style.textFieldPlaceholderColor
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Username icon",
                    tint = style.textFieldIconColor
                )
            },
            singleLine = true,
            shape = style.fieldShape,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = style.textFieldFocusedBackgroundColor,
                unfocusedContainerColor = style.textFieldBackgroundColor,
                disabledContainerColor = style.textFieldBackgroundColor,
                focusedBorderColor = style.textFieldFocusedBorderColor,
                unfocusedBorderColor = style.textFieldBorderColor,
                disabledBorderColor = style.textFieldBorderColor,
                focusedTextColor = style.textFieldTextColor,
                unfocusedTextColor = style.textFieldTextColor,
                focusedLeadingIconColor = style.textFieldIconColor,
                unfocusedLeadingIconColor = style.textFieldIconColor,
                focusedPlaceholderColor = style.textFieldPlaceholderColor,
                unfocusedPlaceholderColor = style.textFieldPlaceholderColor
            ),
            textStyle = style.textFieldTextStyle,
            modifier = style.fieldModifier.fillMaxWidth(),
            enabled = !state.isLoading
        )

        Spacer(modifier = Modifier.height(style.spacingBetweenFields))

        OutlinedTextField(
            value = state.password,
            onValueChange = { onAction(AuthAction.UpdatePassword(it)) },
            placeholder = {
                Text(
                    text = config.passwordLabel,
                    style = style.textFieldTextStyle,
                    color = style.textFieldPlaceholderColor
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password icon",
                    tint = style.textFieldIconColor
                )
            },
            singleLine = true,
            shape = style.fieldShape,
            visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = if (config.showPasswordToggle) {
                {
                    IconButton(onClick = { onAction(AuthAction.TogglePasswordVisibility) }) {
                        Icon(
                            if (state.isPasswordVisible) {
                                Icons.Default.Visibility
                            } else Icons.Default.VisibilityOff,
                            contentDescription = if (state.isPasswordVisible) "Hide" else "Show",
                            tint = style.textFieldIconColor
                        )
                    }
                }
            } else null,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = style.textFieldFocusedBackgroundColor,
                unfocusedContainerColor = style.textFieldBackgroundColor,
                disabledContainerColor = style.textFieldBackgroundColor,
                focusedBorderColor = style.textFieldFocusedBorderColor,
                unfocusedBorderColor = style.textFieldBorderColor,
                disabledBorderColor = style.textFieldBorderColor,
                focusedTextColor = style.textFieldTextColor,
                unfocusedTextColor = style.textFieldTextColor,
                focusedLeadingIconColor = style.textFieldIconColor,
                unfocusedLeadingIconColor = style.textFieldIconColor,
                focusedTrailingIconColor = style.textFieldIconColor,
                unfocusedTrailingIconColor = style.textFieldIconColor,
                focusedPlaceholderColor = style.textFieldPlaceholderColor,
                unfocusedPlaceholderColor = style.textFieldPlaceholderColor
            ),
            textStyle = style.textFieldTextStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = style.fieldModifier.fillMaxWidth(),
            enabled = !state.isLoading
        )

        Spacer(modifier = Modifier.height(style.spacingBeforeButton))

        Button(
            onClick = { onAction(AuthAction.LoginClicked) },
            enabled = state.isBasicAuthLoginButtonEnabled && !state.isLoading,
            shape = style.buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = style.buttonBackgroundColor,
                disabledContainerColor = style.buttonDisabledBackgroundColor
            ),
            modifier = style.fieldModifier
                .fillMaxWidth()
                .height(style.buttonHeight)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = style.buttonLoadingIndicatorColor,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = config.loginButtonLabel,
                    style = style.buttonTextStyle,
                    color = style.buttonTextColor
                )
            }
        }

        if (config.forgotPasswordLabel != null) {
            TextButton(
                onClick = { onAction(AuthAction.ForgotPasswordClicked) },
                enabled = !state.isLoading
            ) {
                Text(config.forgotPasswordLabel)
            }
        }

        if (footerContent != null) {
            Spacer(modifier = Modifier.weight(1f))
            footerContent()
        }
    }
}
