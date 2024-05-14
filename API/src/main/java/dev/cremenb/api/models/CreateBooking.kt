package dev.cremenb.api.models

data class CreateBooking (
    val checkIn : String,
    val checkOut : String,
    val placeId : Int,
)