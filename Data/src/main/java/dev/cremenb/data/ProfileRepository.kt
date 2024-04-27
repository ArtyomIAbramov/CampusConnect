package dev.cremenb.data

import dev.cremenb.api.IApi
import dev.cremenb.data.models.Profile
import dev.cremenb.database.DataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileRepository(
    private  val db : DataBase,
    private val api : IApi
) {
    suspend fun getProfile() : Flow<Profile> {
        return db.profileDao()
            .getProfile()
            .map { it -> it.toProfile()  }
       TODO ("not implemented")
    }
}