package dev.cremenb.data

import dev.cremenb.api.models.Profile
import dev.cremenb.database.models.ProfileDbo

fun Profile.toProfileDbo() : ProfileDbo{
    return ProfileDbo(
        this.id!!,
        this.name,
        this.surname,
        this.genderId,
        this.phone,
        this.email,
        this.token
    )
}

fun ProfileDbo.toProfile(): Profile
{
    TODO("Not yet implemented")
}