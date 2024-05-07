package dev.cremenb.campus_connect.ui.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.cremenb.data.ProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (
    val repository: ProfileRepository
) : ViewModel() {

}
