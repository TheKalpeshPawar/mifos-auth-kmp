/*
 * Copyright 2026 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 */
package org.mifos.auth.kmp.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    var id: Int = 0,

    var name: String? = null,

    var description: String? = null,
)
