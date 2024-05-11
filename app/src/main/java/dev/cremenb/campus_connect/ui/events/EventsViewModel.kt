package dev.cremenb.campus_connect.ui.events

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.EventsRepository
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor (
    private val repository: EventsRepository,
) : ViewModel() {

}