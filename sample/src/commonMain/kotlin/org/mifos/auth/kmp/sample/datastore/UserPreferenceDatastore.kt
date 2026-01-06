package org.mifos.auth.kmp.sample.datastore

import com.russhwolf.settings.Settings
import kotlinx.serialization.json.Json
import org.mifos.auth.kmp.sample.model.User

const val USER = "user"

class UserPreferenceDatastore(
    val settings: Settings
) {

    fun saveUser(user: User) {
        settings.putString(USER, Json.encodeToString(user))
    }

    fun getUser(): User? {
        val userString = settings.getStringOrNull(USER)

        return if (userString != null) {
            Json.decodeFromString(userString)
        } else null
    }

    fun deleteUser(){
        settings.remove(USER)
    }

}