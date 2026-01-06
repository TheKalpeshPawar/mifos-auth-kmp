package org.mifos.auth.kmp.core.common.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerConfig(
    val protocol: String,
    @SerialName("end_point")
    val endPoint: String,
    @SerialName("api_path")
    val apiPath: String,
    val port: String,
    val tenant: String,
) {
    val baseUrl = "$protocol$endPoint:$port$apiPath"
    companion object {
        const val AUTHENTICATION_ENDPOINT = "authentication"
        val DEFAULT = ServerConfig(
            protocol = "https://",
            endPoint = "tt.mifos.community",
            apiPath = "/fineract-provider/api/v1/",
            port = "443",
            tenant = "default",
        )

        val LOCALHOST = ServerConfig(
            protocol = "http://",
            endPoint = "localhost",
            apiPath = "/fineract-provider/api/v1/",
            port = "8080",
            tenant = "default",
        )
    }
}