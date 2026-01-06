package org.mifos.auth.kmp.core.network

import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HeadersBuilder
import io.ktor.http.ParametersBuilder
import io.ktor.http.isSuccess
import org.mifos.auth.kmp.core.common.utils.ServerConfig
import org.mifos.auth.kmp.core.network.model.authentication.PostAuthenticationRequest
import org.mifos.auth.kmp.core.network.model.authentication.PostAuthenticationResponse


class Authenticator(
    private val serverConfig: ServerConfig = ServerConfig.DEFAULT,
) {
    private val httpClient = KtorHttpClient

    suspend fun authenticate(
        username: String,
        password: String,
        queryParameters: ParametersBuilder.()-> Unit ={},
        additionalHeaders: HeadersBuilder.() -> Unit = {}
    ): PostAuthenticationResponse {
        try {
            val result = httpClient.post {
                url(serverConfig.baseUrl + "authentication")
                url.parameters.apply(queryParameters)

                headers {
                    append("Accept", "application/json")
                    append("Accept-Charset", "UTF-8")
                    append("Content-Type", "application/json")
                    append("Fineract-Platform-TenantId", "default")
                    additionalHeaders()
                }

                setBody(
                    PostAuthenticationRequest(
                        password.trim(),
                        username.trim(),
                    )
                )

            }

            if (!result.status.isSuccess()) {
                throw Exception(result.bodyAsText())
            }

            return result.body()
        } catch (e: Exception) {
            throw e
        }
    }
}