package dev.cremenb.data

import dev.cremenb.api.IProfile
import dev.cremenb.api.models.ProfileDto
import dev.cremenb.data.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.database.DataBase
import dev.cremenb.database.models.ProfileDbo
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onEach

class ProfileRepository @Inject constructor(
    private val db : DataBase,
    private val api : IProfile,
) {
    fun getProfile(
        mergeStrategy: MergeStrategy<RequestResult<Profile>> = RequestResultMergeStrategy()
    ) : Flow<RequestResult<Profile>> {

        val localCache: Flow<RequestResult<Profile>> = getProfileFromDataBase()

        val remote: Flow<RequestResult<Profile>> = getProfileFromServer()

        return localCache.combine(remote, mergeStrategy::merge)
        }
    private fun getProfileFromServer(): Flow<RequestResult<Profile>> {
        val apiRequest = flow{ emit(api.getProfile())}
            .onEach { result ->
                if(result.isSuccess)
                {
                    val dtos = result.getOrNull()?.body()?.toProfileDbo()
                    if (dtos != null)
                        db.profileDao().insertProfile(dtos)
                }
            }
            .map { response -> response.map { it.body()!! }}
            .map { it.toRequestResult() }
            .map {result -> result.map { it!!.toProfile() }}

        val start = flowOf<RequestResult<Profile>>(RequestResult.InProgress())
        return merge(apiRequest, start)
    }

    private fun getProfileFromDataBase(): Flow<RequestResult<Profile>> {
        val dbRequest = db
            .profileDao()
            .getProfile()
            .map { RequestResult.Success(it) }
            .map {result -> result.map { it!!.toProfile() }}

        val start = flowOf<RequestResult<Profile>>(RequestResult.InProgress())

        return merge(start, dbRequest)
    }

    fun fetchLatest(): Flow<RequestResult<Profile>> {
        return getProfileFromServer()
        TODO("Not yet implemented")
    }
}