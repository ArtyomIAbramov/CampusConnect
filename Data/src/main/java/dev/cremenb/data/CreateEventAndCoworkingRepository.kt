package dev.cremenb.data

import dev.cremenb.api.ICreateEventAndCoworking
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.utilities.DateFormatter
import jakarta.inject.Inject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class CreateEventAndCoworkingRepository  @Inject constructor(
    private val api : ICreateEventAndCoworking,
)
{

    suspend fun getAvailablePlaces(date : Date) : RequestResult<List<PlaceAndSlot>> {

        val dateIso = DateFormatter.formatDateToIsoString(date)

        val response = handleApi { api.getAvailableSlotsAndPlaces(dateIso)}

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