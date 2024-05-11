package dev.cremenb.api.models

data class Place (
    val id : Int?,
    val name : String?,
    val address : String?,
    val location : String?,
    val description : String?,
    val capacity : Int?,
    val isBlocked : Boolean?,
    val university : University?,
    val types : MutableList<PlaceType>?,
    val events : MutableList<Event>?,
    val bookings : MutableList<Booking>?,
)