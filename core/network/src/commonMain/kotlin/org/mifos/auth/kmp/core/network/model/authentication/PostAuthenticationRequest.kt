package org.mifos.auth.kmp.core.network.model.authentication

import kotlinx.serialization.Serializable

/**
 * PostAuthenticationRequest
 *
 * @param password
 * @param username
 */

@Serializable
data class PostAuthenticationRequest(
    val password: String,
    val username: String,
)
