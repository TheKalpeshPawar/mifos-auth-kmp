package org.mifos.auth.kmp.library

import io.ktor.http.HeadersBuilder
import io.ktor.http.ParametersBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.mifos.auth.kmp.core.common.utils.DataState
import org.mifos.auth.kmp.core.common.utils.asDataStateFlow
import org.mifos.auth.kmp.core.network.Authenticator
import org.mifos.auth.kmp.core.network.model.authentication.PostAuthenticationResponse

class BasicAuthentication(
    private val authenticator: Authenticator
) {
    operator fun invoke(
        username: String,
        password: String,
        queryParameters: ParametersBuilder.() -> Unit = {},
        additionalHeaders: HeadersBuilder.() -> Unit = {}
    ) : Flow<DataState<PostAuthenticationResponse>> = flow {
        emit(
            authenticator.authenticate(
                username,
                password,
                queryParameters,
                additionalHeaders
            )
        )
    }.asDataStateFlow()
}