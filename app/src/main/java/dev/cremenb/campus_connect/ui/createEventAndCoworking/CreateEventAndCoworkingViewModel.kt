package dev.cremenb.campus_connect.ui.createEventAndCoworking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Event
import dev.cremenb.api.models.Place
import dev.cremenb.data.CreateEventAndCoworkingRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CreateEventAndCoworkingViewModel @Inject constructor (
    private val repository: CreateEventAndCoworkingRepository,
) : ViewModel(){

    var placesResult = MutableLiveData<RequestResult<List<Place>>>()

    var allPlaces: List<Place>? = null

    fun getPlaces() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAvailablePlaces()
                placesResult.postValue(response)
            }
        }
    }
}