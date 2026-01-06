package org.mifos.auth.kmp.core.network.model.authentication

import kotlinx.serialization.Serializable

/**
 *
 *
 * @param code
 * @param id
 * @param `value`
 */

@Serializable
data class EnumOptionData(

    val code: String? = null,

    val id: Long? = null,

    val value: String? = null,
)

