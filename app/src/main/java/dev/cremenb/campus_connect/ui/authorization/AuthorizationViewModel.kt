package dev.cremenb.campus_connect.ui.authorization

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.AuthorizationRepository
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor (
    val repository: AuthorizationRepository
) : ViewModel() {
}