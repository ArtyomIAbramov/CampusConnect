package dev.cremenb.campus_connect.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.AuthorizationRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    val repository: AuthorizationRepository
) : ViewModel() {
}