package dev.cremenb.data

import dev.cremenb.api.IEventComments
import dev.cremenb.api.models.Comment
import dev.cremenb.api.models.CommentData
import dev.cremenb.data.models.RequestResult
import dev.cremenb.data.models.handleApi
import dev.cremenb.database.DataBase
import jakarta.inject.Inject

class CommentsRepository @Inject constructor(
    private val db : DataBase,
    private val api : IEventComments,
)
{
    suspend fun postComment(commentData: CommentData) : RequestResult<List<Comment>> {

        val token = db
            .profileDao()
            .getToken()

        val response = handleApi { api.postComment("Bearer " + token, commentData)}

        return when (response) {
            is RequestResult.Success -> {
                return RequestResult.Success(response.data)
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

    suspend fun getComments(eventId : String) : RequestResult<List<Comment>> {

        val response = handleApi { api.getComment(eventId)}

        return when (response) {
            is RequestResult.Success -> {
                return RequestResult.Success(response.data)
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