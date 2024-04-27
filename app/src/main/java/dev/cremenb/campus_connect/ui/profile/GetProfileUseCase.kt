package dev.cremenb.campus_connect.ui.profile

import dev.cremenb.data.ProfileRepository
import dev.cremenb.data.models.Profile
import kotlinx.coroutines.flow.Flow

class GetProfileUseCase(
    private val repository : ProfileRepository
) {
    operator suspend fun invoke(): Flow<Profile> {
        return repository.getProfile()
    }

}