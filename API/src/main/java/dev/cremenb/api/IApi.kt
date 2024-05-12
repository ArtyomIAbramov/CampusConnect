package dev.cremenb.api

import dev.cremenb.api.models.Booking
import dev.cremenb.api.models.Event
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.Register
import dev.cremenb.api.models.University
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface IProfile{
    @GET("Users/getbylogin")
    suspend fun getProfileBylogin(@Query("login") login: String) : Response<Profile>
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

interface IEvents{
    @GET("events")
    suspend fun getEvents() : Response<List<Event>>
}

interface ICreateEventAndCoworking{
    @GET("events")
    suspend fun getAvailableEvents() : Response<List<Event>>

    @GET("events")
    suspend fun getCoworking() : Response<List<Booking>>
}