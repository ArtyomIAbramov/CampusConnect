package dev.cremenb.data

import dev.cremenb.api.IAuthorization
import dev.cremenb.api.IRegistration
import dev.cremenb.api.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject

class RegistrationRepository  @Inject constructor(
    private val api : IRegistration,
) {
    suspend fun register(profile: Profile) : RequestResult<Void> {

        val response = handleApi { api.register(profile)}

        return when (response) {
            is RequestResult.Success -> {
                RequestResult.Success()
            }

            is RequestResult.Error -> {
                RequestResult.Error(response.code, response.message)
            }

            is RequestResult.Exception -> {
                RequestResult.Exception(response.e)
            }
        }
    }
}