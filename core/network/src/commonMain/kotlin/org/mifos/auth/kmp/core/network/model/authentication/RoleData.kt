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

