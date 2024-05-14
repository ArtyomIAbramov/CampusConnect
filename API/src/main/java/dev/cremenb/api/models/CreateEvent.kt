package dev.cremenb.api.models

data class CreateEvent (
    val placeId : Int,
    val name : String,
    val description : String,
    val eventTypeId : Int,
    val date : String,
)