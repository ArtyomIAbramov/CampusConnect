package dev.cremenb.api.models

data class Profile(
    val id : String?,
    val name : String?,
    val surname : String?,
    val login : String?,
    val genderId : Int?,
    val phone : String?,
    val email : String?,
    val token : String?,
    val password : String?,
    //val recordBook : String?,
    //val university : University?,
    //val password : String?,
)