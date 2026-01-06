package org.mifos.auth.kmp.sample.util

import org.mifos.auth.kmp.core.network.model.authentication.PostAuthenticationResponse
import org.mifos.auth.kmp.core.network.model.authentication.RoleData
import org.mifos.auth.kmp.sample.model.Role
import org.mifos.auth.kmp.sample.model.User


fun PostAuthenticationResponse.toUser(
    isAuthenticated: Boolean = false
): User {
    return User(
        username = this.username,
        userId = this.userId ?: 0,
        base64EncodedAuthenticationKey = this.base64EncodedAuthenticationKey,
        isAuthenticated = isAuthenticated,
        officeId = this.officeId ?: 0,
        officeName = this.officeName,
        roles = this.roles?.map { it.toRole() } ?: emptyList(),
        permissions = this.permissions ?: emptyList()
    )
}

fun RoleData.toRole(): Role {
    return Role (
        id = this.id?.toInt() ?: 0,
        name = this.name,
        description = null
    )
}
