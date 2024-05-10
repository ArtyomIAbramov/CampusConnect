package dev.cremenb.api.models

import java.util.Date


data class Booking (
    val id : String?,
    val user : Profile?,
    val checkIn : Date?,
    val checkOut : Date?,
    val status : Boolean?,
    val createdAt : Date?,
    val place : Place?,
)