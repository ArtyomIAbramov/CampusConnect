package dev.cremenb.database.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.cremenb.api.models.Gender
import dev.cremenb.api.models.University

@Entity(tableName = "Profile")
class ProfileDbo (
    @PrimaryKey(autoGenerate = false) val id : String,
    @ColumnInfo("name") val name : String?,
    @ColumnInfo("surname") val surname : String?,
    @ColumnInfo("login") val login : String?,
    @ColumnInfo("phone") val phone : String?,
    @ColumnInfo("email") val email : String?,
    @ColumnInfo("token") val token : String?,
    @ColumnInfo("avatar") val avatar : String?,
    @ColumnInfo("card") val card : String?,
)