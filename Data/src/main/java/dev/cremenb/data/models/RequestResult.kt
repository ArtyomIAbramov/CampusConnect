package dev.cremenb.data.models

sealed class RequestResult<T> (
    private val data: T? = null
) {
    class InProgress<T>(data: T? = null) : RequestResult<T>(data)
    class Success<T>(data: T) : RequestResult<T>(data)
    class Error<T>(data: T? = null, val error: Throwable? = null): RequestResult<T>()

    fun requireData(): T = checkNotNull(data)

    fun getData() : T? = data

    fun<O> map(mapper : (T) -> O): RequestResult<O>{
        return when(this)
        {
            is Success -> {
                val outData = mapper(requireData())
                RequestResult.Success(outData)
            }
            is Error -> RequestResult.Error(data?.let(mapper))
            is InProgress -> RequestResult.InProgress(data?.let(mapper))
        }
    }
}

