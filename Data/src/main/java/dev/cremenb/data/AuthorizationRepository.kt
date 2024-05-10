package dev.cremenb.data

import dev.cremenb.api.IAuthorization
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val db : DataBase,
    private val api : IAuthorization,
) {
    suspend fun login(lodinData : Login) : RequestResult<Profile> {

        val response = handleApi { api.login(lodinData)}

        return when (response) {
            is RequestResult.Success -> {
                db.profileDao().insertProfile(response.data!!.toProfileDbo())
                return RequestResult.Success(response.data)
            }

            is RequestResult.Error -> {
                RequestResult.Error(response.code, response.message)
            }

            is RequestResult.Exception -> {
                RequestResult.Exception(response.e)
            }
            is RequestResult.InProgress -> RequestResult.InProgress()
        }
    }

    suspend fun isAuthenticated() : RequestResult<Void>{

        val token = db
            .profileDao()
            .getToken()

        if(token == null)
        {
            return RequestResult.Error(401, "Token doesn`t found" )
        }
        else
        {
            val response = handleApi { api.isAuthenticated("Bearer " + token)}

            return when (response) {
                is RequestResult.Success -> {
                    RequestResult.Success()
                }

                is RequestResult.Error -> {
                    RequestResult.Error(response.code, response.message)
                }

                is RequestResult.Exception -> {
                    RequestResult.Exception(response.e)
                }
                is RequestResult.InProgress -> RequestResult.InProgress()
            }
        }
    }
}