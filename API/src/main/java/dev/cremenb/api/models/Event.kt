package dev.cremenb.api.models

import java.util.Date

data class Event (
    val id : String?,
    val name : String?,
    val thumbnail : String?,
    val description : String?,
    val date : Date?,
    val status : String?,
    val address : String?,
    val place : Place?,
    val events : MutableList<Event> = mutableListOf()
)