package dev.cremenb.data

import dev.cremenb.data.models.RequestResult

interface MergeStrategy<T> {
    fun merge(right : T, left : T) : T
}

internal class RequestResultMergeStrategy<T> : MergeStrategy<RequestResult<T>>{

    override fun merge(right: RequestResult<T>, left: RequestResult<T>): RequestResult<T> {
        return when{
            right is RequestResult.InProgress && left is RequestResult.InProgress -> merge(right, left)
            right is RequestResult.Success && left is RequestResult.InProgress -> merge(right, left)
            right is RequestResult.InProgress && left is RequestResult.Success -> merge(right, left)
            right is RequestResult.Success && left is RequestResult.Error -> merge(right, left)
            else -> TODO("Not yet implemented")
        }
    }

    private fun merge(
        cache: RequestResult.InProgress<T>,
        server: RequestResult.InProgress<T>,
    ) : RequestResult<T>
    {
        return when{
            server.getData()!=null -> RequestResult.InProgress(server.getData())
            else -> RequestResult.InProgress(cache.getData())
        }
    }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.InProgress<T>,
    ) : RequestResult<T>
    {
        return RequestResult.InProgress(cache.getData())
    }

    private fun merge(
        cache: RequestResult.InProgress<T>,
        server: RequestResult.Success<T>,
    ) : RequestResult<T>
    {
        return RequestResult.InProgress(server.getData())
    }

    private fun merge(
        cache: RequestResult.Success<T>,
        server: RequestResult.Error<T>,
    ) : RequestResult<T>
    {
        return RequestResult.Error(cache.getData(), error = server.error)
    }
}