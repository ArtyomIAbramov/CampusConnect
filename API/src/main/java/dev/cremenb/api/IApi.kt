package dev.cremenb.api

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.cremenb.api.models.SomeResultDTO
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface IApi {

    // TODO change when api will be ready
    @GET("/something")
    suspend fun something(
        @Query("send_parametr") parametr : String? = null,
    ) : Result<Response<SomeResultDTO>>
}

fun Api(
    baseUrl : String,
    okHttpClient: OkHttpClient? = null,
) : IApi
{
    val retrofit = retrofit(baseUrl, okHttpClient)
    return retrofit.create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
): Retrofit {

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .run { if (okHttpClient != null) client(okHttpClient) else this }
        .build()

    return retrofit
}