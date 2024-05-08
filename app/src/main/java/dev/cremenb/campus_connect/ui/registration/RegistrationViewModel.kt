package dev.cremenb.campus_connect.ui.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.RegistrationRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel  @Inject constructor (
    val repository: RegistrationRepository
) : ViewModel() {
}