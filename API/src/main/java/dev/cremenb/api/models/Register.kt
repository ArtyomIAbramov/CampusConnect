package dev.cremenb.api.models

data class Register(
    val name : String,
    val surname : String,
    val login : String,
    val genderId : Int,
    val password : String,
    val universityId : Int,
)