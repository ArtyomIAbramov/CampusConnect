package dev.cremenb.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dev.cremenb.database.models.ProfileDbo

@Dao
interface IProfileDao {

    //TODO change Query request
    @Query("SELECT * FROM Profile")
    fun getProfile() : ProfileDbo?

    @Query("SELECT token FROM Profile")
    fun getToken() : String?

    @Insert
    fun insertProfile(profile : ProfileDbo)

    @Delete
    fun delete(profile : ProfileDbo)
}