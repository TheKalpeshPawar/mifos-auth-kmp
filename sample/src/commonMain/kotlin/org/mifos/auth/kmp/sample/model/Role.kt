package org.mifos.auth.kmp.sample.model

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    var id: Int = 0,

    var name: String? = null,

    var description: String? = null,
)

