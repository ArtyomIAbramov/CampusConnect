package dev.cremenb.data

import dev.cremenb.api.models.ProfileDto
import dev.cremenb.data.models.Profile
import dev.cremenb.data.models.RequestResult
import dev.cremenb.database.models.ProfileDbo

internal fun ProfileDbo.toProfile() : Profile{
    TODO("not implemented")
}

internal fun ProfileDto.toProfileDbo() : ProfileDbo{
    TODO("Not yet implemented")
}

internal fun ProfileDto.toProfile() : Profile{
    TODO("Not yet implemented")
}

internal fun<T> Result<T>.toRequestResult() : RequestResult<T>
{
    return when
    {
        isSuccess -> RequestResult.Success(this.getOrThrow())
        isFailure -> RequestResult.Error()
        else -> error("Impossible")
    }
}
