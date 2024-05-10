package dev.cremenb.api.models

data class Profile(
    val id : String,
    val name : String?,
    val surname : String?,
    val login : String?,
    val gender : Gender?,
    val phone : String?,
    val email : String?,
    val token : String?,
    val university : University?,
    val avatar : String?,
    val card : String?,
)

data class Gender(
    val id : Int,
    val name : String,
)
