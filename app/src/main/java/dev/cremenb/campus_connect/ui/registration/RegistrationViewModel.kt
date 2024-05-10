package dev.cremenb.campus_connect.ui.registration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.Register
import dev.cremenb.data.AuthorizationRepository
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.RegistrationRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel  @Inject constructor (
    private val repository: RegistrationRepository,
    private val loginRepository: AuthorizationRepository
) : ViewModel() {

    var registrationResult = MutableLiveData<RequestResult<Void>>()

    fun register(name : String, surname : String, login : String, genderId : Int, password : String, universityId : Int) {
        val registerData = Register(name, surname,login,genderId,password, universityId)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.register(registerData)
                if (response is RequestResult.Success)
                    loginRepository.login(Login(login,password))
                registrationResult.postValue(response)
            }
        }
    }
}