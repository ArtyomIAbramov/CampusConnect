package dev.cremenb.api.models

data class PlaceType (
    val id : Int?,
    val name : String?,
    val places : MutableList<Place>?,
)