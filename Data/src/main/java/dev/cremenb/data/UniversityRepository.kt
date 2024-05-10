package dev.cremenb.data

import dev.cremenb.api.IUniversity
import dev.cremenb.api.models.University
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import jakarta.inject.Inject

class UniversityRepository @Inject constructor(
    private val api : IUniversity,
)
{
    suspend fun getUniversities() : RequestResult<List<University>> {

        val response = handleApi { api.getUniversities() }

        return when (response) {
            is RequestResult.Success -> {
                RequestResult.Success(response.data)
            }

            is RequestResult.Error -> {
                RequestResult.Error(response.code, response.message)
            }

            is RequestResult.Exception -> {
                RequestResult.Exception(response.e)
            }
            is RequestResult.InProgress -> RequestResult.InProgress()
        }
    }
}