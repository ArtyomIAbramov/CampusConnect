package dev.cremenb.campus_connect

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.cremenb.api.IProfile
import dev.cremenb.api.ProfileAPI
import dev.cremenb.database.DataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun profileApi() : IProfile
    {
        return ProfileAPI(
            baseUrl = BuildConfig.API_BASE_URL,
            apiKey = BuildConfig.API_KEY,
        )
    }

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context) : DataBase
    {
        return DataBase(context)
    }
}