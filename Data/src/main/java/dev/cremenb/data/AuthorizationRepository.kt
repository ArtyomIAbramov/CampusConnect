package dev.cremenb.data

import dev.cremenb.api.IAuthorization
import dev.cremenb.api.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject

class AuthorizationRepository @Inject constructor(
    private val db : DataBase,
    private val api : IAuthorization,
) {
    suspend fun login(lodinData : Profile) : RequestResult<Profile> {

        val response = handleApi { api.login(lodinData)}

        return when (response) {
            is RequestResult.Success -> {
                response
            }

            is RequestResult.Error -> {
                RequestResult.Error(response.code, response.message)
            }

            is RequestResult.Exception -> {
                RequestResult.Exception(response.e)
            }
        }
    }

    suspend fun isAuthenticated() : RequestResult<Void>{

        val token = db
            .profileDao()
            .getToken()

        if(token == null)
        {
            val profile = Profile(null, "Artem2","Artem","Artem6",2 ,"Artem","Artem",null,"Artem")
            val response = handleApi { api.login(profile)}

            when (response) {
                is RequestResult.Success -> {
                    db.profileDao().insertProfile(response.data!!.toProfileDbo())
                    return RequestResult.Success()
                }

                is RequestResult.Error -> {
                    return RequestResult.Error(response.code, response.message)
                }

                is RequestResult.Exception -> {
                    return RequestResult.Exception(response.e)
                }
            }
            //return RequestResult.Error(401, "Token doesn`t found" )

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
            }
        }
    }
}