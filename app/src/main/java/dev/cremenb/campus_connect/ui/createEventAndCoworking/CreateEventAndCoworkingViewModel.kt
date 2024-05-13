package dev.cremenb.campus_connect.ui.createEventAndCoworking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.data.CreateEventAndCoworkingRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateEventAndCoworkingViewModel @Inject constructor (
    private val repository: CreateEventAndCoworkingRepository,
) : ViewModel(){

    var placesAndSlotsResult = MutableLiveData<RequestResult<List<PlaceAndSlot>>>()

    var allPlacesAndSlots: List<PlaceAndSlot>? = null

    fun getPlacesAndSlots(date : Date) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAvailablePlaces(date)
                placesAndSlotsResult.postValue(response)
            }
        }
    }
}