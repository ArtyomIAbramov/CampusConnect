package dev.cremenb.campus_connect.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Event
import dev.cremenb.data.EventsRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor (
    private val repository: EventsRepository,
) : ViewModel() {

    var allEvents: List<Event>? = null

    fun takePart(id : String) {
    }
}