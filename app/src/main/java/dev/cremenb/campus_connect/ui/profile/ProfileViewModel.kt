package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.models.Profile
import dev.cremenb.data.models.RequestResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    private val repository: ProfileRepository
) : ViewModel() {

    val state : StateFlow<State> = repository.getProfile()
        .map { requestResult -> requestResult.map {
            it.toUiProfile()
        }}
        .map { it.toState() }
        .stateIn(viewModelScope, SharingStarted.Lazily, State.None)

    fun forceUpdate()
    {
        repository.fetchLatest()
    }
}

private fun Profile.toUiProfile() : Profile {
    TODO("Not yet implemented")
}

private fun RequestResult<Profile>.toState() : State {
    return when(this){
        is RequestResult.Error -> State.Error()
        is RequestResult.InProgress -> State.Loading(getData())
        is RequestResult.Success -> State.Success(requireData())
    }
}

sealed class State {
    object None : State()
    class Loading(val profile: Profile? = null) : State()
    class Error(val profile: Profile? = null) : State()
    class Success(val profile: Profile) : State()
}