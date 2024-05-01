package dev.cremenb.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import dev.cremenb.api.models.ProfileDto
import dev.cremenb.api.models.SomeResultDTO
import kotlinx.serialization.json.Json
import okhttp3.MediaType
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

interface IProfile{
    @GET("/profile")
    suspend fun getProfile(
    ) : Result<Response<ProfileDto>>
}

fun ProfileAPI(
    baseUrl : String,
    apiKey : String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
) : IProfile
{
    return retrofit(baseUrl,apiKey,okHttpClient,json).create()
}

private fun retrofit(
    baseUrl : String,
    apiKey : String,
    okHttpClient: OkHttpClient?,
    json: Json,
): Retrofit {

    val jsonConverterFactory = json.asConverterFactory(MediaType.get("application/json"))

    val modifiedOkHttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(ApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkHttpClient)
        .build()
}