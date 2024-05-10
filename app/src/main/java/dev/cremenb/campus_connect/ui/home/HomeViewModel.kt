package dev.cremenb.campus_connect.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.AuthorizationRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    val repository: AuthorizationRepository
) : ViewModel() {

}