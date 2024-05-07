package dev.cremenb.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
class ProfileDbo (
    @PrimaryKey(autoGenerate = false) val id : String,
    @ColumnInfo("name") val name : String?,
    @ColumnInfo("surname") val surname : String?,
    @ColumnInfo("genderId") val genderId : Int?,
    @ColumnInfo("phone") val phone : String?,
    @ColumnInfo("email") val email : String?,
)