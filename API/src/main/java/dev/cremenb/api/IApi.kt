package dev.cremenb.api

import dev.cremenb.api.models.Profile
import retrofit2.Response
import retrofit2.http.GET

interface IProfile{
    @GET("users?email=user@example.com")
    suspend fun getProfile() : Response<Profile>
}