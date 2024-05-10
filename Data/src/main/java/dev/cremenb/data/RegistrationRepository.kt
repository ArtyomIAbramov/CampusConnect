package dev.cremenb.data

import dev.cremenb.api.IRegistration
import dev.cremenb.api.models.Register
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import jakarta.inject.Inject

class RegistrationRepository  @Inject constructor(
    private val api : IRegistration,
) {
    suspend fun register(register: Register) : RequestResult<Void> {

        val response = handleApi { api.register(register)}

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
            is RequestResult.InProgress -> RequestResult.InProgress()
        }
    }
}