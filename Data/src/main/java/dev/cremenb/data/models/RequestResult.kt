package dev.cremenb.data.models

import retrofit2.HttpException
import retrofit2.Response

sealed class RequestResult<T : Any> {
    class Success<T: Any>(val data: T? = null) : RequestResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : RequestResult<T>()
    class Exception<T: Any>(val e: Throwable) : RequestResult<T>()
}

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): RequestResult<T> {
    return try {
        val response= execute()
        val body = response.body()
        if (response.isSuccessful) {
            RequestResult.Success(body)
        } else {
            RequestResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        RequestResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        RequestResult.Exception(e)
    }
}
