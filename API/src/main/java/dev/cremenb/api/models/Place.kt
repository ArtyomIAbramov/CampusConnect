package dev.cremenb.api.models

data class Place (
    val id : Int? = null,
    val name : String?= null,
    val adress : String?= null,
    val location : String?= null,
    val description : String?= null,
    val capacity : Int?= null,
    val isBlocked : Boolean?= null,
    val workFrom : String?= null,
    val workTo: String?= null,
    val minuteStep : Int?= null,
    val university : University?= null,
    val types : List<PlaceType>?= null,
    val events : List<Event>?= null,
    val bookings : List<Booking>?= null,
)