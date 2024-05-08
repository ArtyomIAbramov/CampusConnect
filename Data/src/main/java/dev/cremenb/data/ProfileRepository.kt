package dev.cremenb.data

import dev.cremenb.api.IProfile
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.University
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

        when (val response = remote) {
            is RequestResult.Success -> {
                return remote
            }

            is RequestResult.Error -> {
                val localCache: Profile = getProfileFromDataBase()
                return RequestResult.Success(localCache)
            }

            is RequestResult.Exception -> {
                val localCache: Profile = getProfileFromDataBase()
                return RequestResult.Success(localCache)
            }
        }
    }

    private fun getProfileFromDataBase(): Profile {

        val dbRequest = db
            .profileDao()
            .getProfile()

        if(dbRequest == null)
        {
            return Profile("qwe","DanyaLox",
                "danylalox@sobaka.ry","DanyaLox", 1, "rwer",
                "wefewf", "fsdf", "DanyaLox")
        }

        return dbRequest.toProfile()
    }
}