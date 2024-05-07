package dev.cremenb.api.models

data class Profile(
    val genderId : Int?,
    val name : String?,
    val email : String?,
    val id : String,
    val phone : String?,
    val surname : String?,
)