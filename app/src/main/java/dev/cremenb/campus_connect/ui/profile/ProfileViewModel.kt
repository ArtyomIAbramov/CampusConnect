package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.api.models.Option
import dev.cremenb.api.models.Profile
import dev.cremenb.campus_connect.R
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    private val repository: ProfileRepository
) : ViewModel() {

    var profileResult = MutableLiveData<RequestResult<Profile>>()

    var profie: Profile? = null

    fun getProfile() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getProfile()
                profileResult.postValue(response)
            }
        }
    }

    fun getOptions() : List<Option> {
        return listOf(
            Option(name = "Новые мероприятия", imageResId = R.drawable.ic_profile_opt_entertainment),
            Option(name = "Создай сам", imageResId = R.drawable.ic_profile_opt_create_event),
            Option(name = "Выход", imageResId = R.drawable.ic_profile_opt_logout)
        )
    }
}
