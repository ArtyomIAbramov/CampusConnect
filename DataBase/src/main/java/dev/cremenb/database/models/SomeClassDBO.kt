package dev.cremenb.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SomeClassDBO (
    @PrimaryKey(autoGenerate = true) val id : Int,
    @ColumnInfo("name") val name : String,
)