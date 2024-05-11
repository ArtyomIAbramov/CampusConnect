package dev.cremenb.data

import dev.cremenb.api.models.Profile
import dev.cremenb.database.models.ProfileDbo

fun Profile.toProfileDbo() : ProfileDbo{
    return ProfileDbo(
        this.id,
        this.name,
        this.surname,
        this.login,
        this.phone,
        this.email,
        this.token,
        this.profileImageUrl,
        this.card,
    )
}

fun ProfileDbo.toProfile(): Profile {
    return Profile(
        this.id,
        this.name,
        this.surname,
        this.login,
        null,
        this.phone,
        this.email,
        this.token,
        null,
        null,
        null,
        this.avatar,
        this.card,
    )
}