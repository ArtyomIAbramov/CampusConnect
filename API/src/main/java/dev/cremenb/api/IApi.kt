package dev.cremenb.api

import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.Register
import dev.cremenb.api.models.University
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface IProfile{
    @GET("users?email=user@example.com")
    suspend fun getProfile() : Response<Profile>
}

interface IRegistration{
    @POST("users/register")
    suspend fun register(@Body register: Register) : Response<Void>
}

interface IAuthorization{
    @POST("users/login")
    suspend fun login(@Body login: Login) : Response<Profile>

    @GET("users/verify")
    suspend fun isAuthenticated(@Header("Authorization") token: String) : Response<Void>
}

interface IUniversity{
    @GET("universities/")
    suspend fun getUniversities() : Response<List<University>>
}