package dev.cremenb.api.models

data class Place (
    val id : Int?,
    val name : String?,
    val adress : String?,
    val location : String?,
    val description : String?,
    val capacity : Int?,
    val isBlocked : Boolean?,
    val workFrom : String?,
    val workTo: String?,
    val minuteStep : Int?,
    val university : University?,
    val types : List<PlaceType>?,
    val events : List<Event>?,
    val bookings : List<Booking>?,
)