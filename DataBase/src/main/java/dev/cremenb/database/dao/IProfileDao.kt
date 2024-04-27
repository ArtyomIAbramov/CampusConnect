package dev.cremenb.database.dao

import androidx.room.Dao
import androidx.room.Query
import dev.cremenb.database.models.ProfileDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface IProfileDao {

    //TODO change Query request
    @Query("SELECT * FROM Profile")
    fun getProfile() : Flow<ProfileDbo>
}