package dev.cremenb.campus_connect.ui.createEventAndCoworking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.CreateBooking
import dev.cremenb.api.models.CreateEvent
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.data.CreateEventAndCoworkingRepository
import dev.cremenb.data.models.RequestResult
import dev.cremenb.utilities.DateFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.invoke.TypeDescriptor
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateEventAndCoworkingViewModel @Inject constructor (
    private val repository: CreateEventAndCoworkingRepository,
) : ViewModel(){

    var placesAndSlotsResult = MutableLiveData<RequestResult<List<PlaceAndSlot>>>()

    var allPlacesAndSlots: List<PlaceAndSlot>? = null

    var eventPlacesResult = MutableLiveData<RequestResult<List<PlaceAndSlot>>>()

    var allEventPlaces: List<PlaceAndSlot>? = null

    var createBookingResult = MutableLiveData<RequestResult<Void>>()

    var createEventResult = MutableLiveData<RequestResult<Void>>()

    fun getPlacesAndSlots(date : Date) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAvailablePlaces(date)
                placesAndSlotsResult.postValue(response)
            }
        }
    }

    fun getEventsPlaces(date : Date) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getEventsPlaces(date)
                eventPlacesResult.postValue(response)
            }
        }
    }

    fun createCoworkong(id : Int, from : Date, to : Date) {
        val fromIso = DateFormatter.formatDateToIsoString(from)
        val toIso = DateFormatter.formatDateToIsoString(to)

        val data = CreateBooking(fromIso, toIso, id)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.createBooking(data)
                createBookingResult.postValue(response)
            }
        }
    }

    fun createEvent(placeid : Int, name : String, description: String, eventTypeId : Int, date : Date) {
        val dateIso = DateFormatter.formatDateToIsoString(date)

        val data = CreateEvent(placeid, name, description, eventTypeId, dateIso)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.createEvent(data)
                createEventResult.postValue(response)
            }
        }
    }
}