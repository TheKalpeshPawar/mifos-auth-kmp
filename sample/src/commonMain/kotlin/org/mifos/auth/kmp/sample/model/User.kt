package org.mifos.auth.kmp.sample.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val username: String? = null,
    val userId: Long = 0,
    val base64EncodedAuthenticationKey: String? = null,
    val isAuthenticated: Boolean = false,
    val officeId: Long = 0,
    val officeName: String? = null,
    val roles: List<Role> = emptyList(),
    val permissions: List<String> = emptyList(),
)

