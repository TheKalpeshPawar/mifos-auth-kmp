/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.core.network.model.authentication

import kotlinx.serialization.Serializable

/**
 *
 *
 * @param id
 * @param name
 */

@Serializable
data class RoleData(

    val id: Long? = null,

    val name: String? = null,

)
