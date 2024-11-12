package dev.cremenb.campus_connect.ui.card

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.EventsRepository
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor (
    private val repository: EventsRepository,
) : ViewModel() {
}