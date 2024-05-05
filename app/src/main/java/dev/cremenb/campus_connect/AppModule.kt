package dev.cremenb.campus_connect

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.cremenb.api.IProfile
import dev.cremenb.database.DataBase
import dev.cremenb.utilities.AppDispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL,)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : DataBase
    {
        return DataBase(context)
    }

    @Provides
    @Singleton
    fun provideAppCoroutinesDispatchers() : AppDispatchers
    {
        return AppDispatchers()
    }

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit) : IProfile
    {
        return retrofit.create(IProfile::class.java)
    }
}