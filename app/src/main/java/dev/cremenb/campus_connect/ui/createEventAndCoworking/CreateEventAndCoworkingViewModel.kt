package dev.cremenb.campus_connect.ui.createEventAndCoworking

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.CreateEventAndCoworkingRepository
import javax.inject.Inject

@HiltViewModel
class CreateEventAndCoworkingViewModel @Inject constructor (
    private val repository: CreateEventAndCoworkingRepository,
) : ViewModel(){
}