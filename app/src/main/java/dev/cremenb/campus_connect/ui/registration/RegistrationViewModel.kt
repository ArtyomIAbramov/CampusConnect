package dev.cremenb.campus_connect.ui.registration

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.AuthorizationRepository
import dev.cremenb.data.RegistrationRepository
import dev.cremenb.data.UniversityRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel  @Inject constructor (
    private val repository: RegistrationRepository,
    private val loginRepository: AuthorizationRepository,
    private val universityRepository: UniversityRepository,
) : ViewModel() {

}