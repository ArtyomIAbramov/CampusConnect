package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.Register
import dev.cremenb.api.models.University
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    val repository: ProfileRepository
) : ViewModel() {

    var profileResult = MutableLiveData<RequestResult<Profile>>()

    fun getProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getProfile()
                profileResult.postValue(response)
            }
        }
    }
}
