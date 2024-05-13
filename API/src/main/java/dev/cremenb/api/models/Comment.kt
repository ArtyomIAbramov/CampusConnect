package dev.cremenb.api.models

import java.util.Date

data class Comment (
    val text : String?,
    val createdAt : Date?,
    val login : String?
)