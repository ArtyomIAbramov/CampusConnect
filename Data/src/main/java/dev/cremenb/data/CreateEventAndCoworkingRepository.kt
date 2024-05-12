package dev.cremenb.data

import dev.cremenb.api.ICreateEventAndCoworking
import dev.cremenb.api.models.Place
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject

class CreateEventAndCoworkingRepository  @Inject constructor(
    private val db : DataBase,
    private val api : ICreateEventAndCoworking,
)
{

    suspend fun getAvailablePlaces() : RequestResult<List<Place>> {

        val response = handleApi { api.getAvailablePlaces()}

        return when (response) {
            is RequestResult.Success -> {
                RequestResult.Success(response.data)
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