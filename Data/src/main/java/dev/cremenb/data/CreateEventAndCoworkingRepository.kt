package dev.cremenb.data

import dev.cremenb.api.ICreateEventAndCoworking
import dev.cremenb.api.models.CreateBooking
import dev.cremenb.api.models.CreateEvent
import dev.cremenb.api.models.EventDate
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import dev.cremenb.utilities.DateFormatter
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CreateEventAndCoworkingRepository  @Inject constructor(
    private val api : ICreateEventAndCoworking,
    private val db : DataBase,
)
{
    suspend fun getAvailablePlaces(date : Date) : RequestResult<List<PlaceAndSlot>> {

        val dateIso = DateFormatter.formatDateToIsoString(date)

        val response = handleApi { api.getAvailableSlotsAndPlaces(EventDate(dateIso))}

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

    suspend fun getEventsPlaces(date : Date) : RequestResult<List<PlaceAndSlot>> {

        val dateIso = DateFormatter.formatDateToIsoString(date)

        val response = handleApi { api.getEventsPlaces(EventDate(dateIso))}

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

    suspend fun createBooking(booking: CreateBooking) : RequestResult<Void> {

        val token = db
            .profileDao()
            .getToken()

        val response = handleApi { api.createBooking("Bearer " + token, booking)}

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

    suspend fun createEvent(event: CreateEvent) : RequestResult<Void> {

        val token = db
            .profileDao()
            .getToken()

        val response = handleApi { api.createEvent("Bearer " + token, event)}

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