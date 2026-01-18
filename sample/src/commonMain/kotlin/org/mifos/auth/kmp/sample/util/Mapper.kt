/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.util

import org.mifos.auth.kmp.core.network.model.authentication.PostAuthenticationResponse
import org.mifos.auth.kmp.core.network.model.authentication.RoleData
import org.mifos.auth.kmp.sample.model.Role
import org.mifos.auth.kmp.sample.model.User

fun PostAuthenticationResponse.toUser(
    isAuthenticated: Boolean = false,
): User {
    return User(
        username = this.username,
        userId = this.userId ?: 0,
        base64EncodedAuthenticationKey = this.base64EncodedAuthenticationKey,
        isAuthenticated = isAuthenticated,
        officeId = this.officeId ?: 0,
        officeName = this.officeName,
        roles = this.roles?.map { it.toRole() } ?: emptyList(),
        permissions = this.permissions ?: emptyList(),
    )
}

fun RoleData.toRole(): Role {
    return Role(
        id = this.id?.toInt() ?: 0,
        name = this.name,
        description = null,
    )
}
