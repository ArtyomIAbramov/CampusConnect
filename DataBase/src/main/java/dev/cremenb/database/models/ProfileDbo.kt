package dev.cremenb.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Profile")
class ProfileDbo (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo("name") val name : String,
)