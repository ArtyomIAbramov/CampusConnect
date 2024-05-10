package dev.cremenb.campus_connect.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.data.AuthorizationRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    val repository: AuthorizationRepository
) : ViewModel() {

    var authenticationResult = MutableLiveData<RequestResult<Void>>()

    var loginResult = MutableLiveData<RequestResult<Profile>>()

    fun checkAuthentication() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.isAuthenticated()
                authenticationResult.postValue(response)
            }
        }
    }

    fun login(login : String, password : String) {
        val loginData = Login(login,password)
         viewModelScope.launch {
             withContext(Dispatchers.IO) {
                 val response = repository.login(loginData)
                 loginResult.postValue(response)
             }
         }
    }
}