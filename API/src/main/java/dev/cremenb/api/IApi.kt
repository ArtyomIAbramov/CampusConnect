package dev.cremenb.api

import dev.cremenb.api.models.Booking
import dev.cremenb.api.models.Comment
import dev.cremenb.api.models.CommentData
import dev.cremenb.api.models.CreateBooking
import dev.cremenb.api.models.CreateEvent
import dev.cremenb.api.models.Event
import dev.cremenb.api.models.EventDate
import dev.cremenb.api.models.Login
import dev.cremenb.api.models.Place
import dev.cremenb.api.models.PlaceAndSlot
import dev.cremenb.api.models.Profile
import dev.cremenb.api.models.Register
import dev.cremenb.api.models.University
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.Date

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
    suspend fun getEvents(@Header("Authorization") token: String) : Response<List<Event>>

    @POST("eventparticipants?")
    suspend fun takePart(@Header("Authorization") token: String, @Query("eventId") eventId: String) : Response<Void>
}

interface ICreateEventAndCoworking{
    @POST("place/slots")
    suspend fun getAvailableSlotsAndPlaces(@Body data : EventDate) : Response<List<PlaceAndSlot>>

    @GET("place/events?")
    suspend fun getEventsPlaces(@Query("date") date : String) : Response<List<Place>>

    @POST("booking")
    suspend fun createBooking(@Header("Authorization") token: String, @Body booking: CreateBooking) : Response<Void>

    @POST("booking")
    suspend fun createEvent(@Header("Authorization") token: String, @Body booking: CreateEvent) : Response<Void>
}

interface IEventComments{
    @POST("eventcomments")
    suspend fun postComment(@Header("Authorization") token: String, @Body commentData : CommentData) : Response<List<Comment>>

    @GET("eventcomments?")
    suspend fun getComment(@Query("eventId") eventId: String) : Response<List<Comment>>
}