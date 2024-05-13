package dev.cremenb.api.models

import java.util.Date

data class Event (
    val id : String?,
    val name : String?,
    val thumbnail : String?,
    val description : String?,
    val date : Date?,
    val place: Place?,
    val status : EventStatus?,
    val users : List<Profile>?,
    val isParticipant : Boolean?,
)