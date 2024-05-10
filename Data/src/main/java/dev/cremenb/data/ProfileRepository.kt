package dev.cremenb.data

import dev.cremenb.api.IProfile
import dev.cremenb.api.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject


class ProfileRepository @Inject constructor(
    private val db : DataBase,
    private val api : IProfile,
) {

    suspend fun getProfile() : RequestResult<Profile> {

        val remote = handleApi { api.getProfile() }

        return when (val response = remote) {
            is RequestResult.Success -> {
                remote
            }

            is RequestResult.Error, is RequestResult.Exception-> {
                getProfileFromDataBase()
            }

            is RequestResult.InProgress -> RequestResult.InProgress()
        }
    }

    private fun getProfileFromDataBase(): RequestResult<Profile> {

        val dbRequest = db
            .profileDao()
            .getProfile() ?: return RequestResult.Error(404, "")

        return RequestResult.Success(dbRequest.toProfile())
    }
}
