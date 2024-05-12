package dev.cremenb.data

import dev.cremenb.api.IEvents
import dev.cremenb.api.models.Event
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import jakarta.inject.Inject

class EventsRepository @Inject constructor(
    private val api : IEvents,
)
{
    suspend fun getAllEvents() : RequestResult<List<Event>> {

        val response = handleApi { api.getEvents()}

        return when (response) {
            is RequestResult.Success -> {
                return RequestResult.Success(response.data)
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