package dev.cremenb.campus_connect

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.cremenb.api.IAuthorization
import dev.cremenb.api.IEvents
import dev.cremenb.api.IProfile
import dev.cremenb.api.IRegistration
import dev.cremenb.api.IUniversity
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

    @Provides
    @Singleton
    fun provideRegisterApi(retrofit: Retrofit) : IRegistration
    {
        return retrofit.create(IRegistration::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthorizationApi(retrofit: Retrofit) : IAuthorization
    {
        return retrofit.create(IAuthorization::class.java)
    }

    @Provides
    @Singleton
    fun provideUniversitiesApi(retrofit: Retrofit) : IUniversity
    {
        return retrofit.create(IUniversity::class.java)
    }

    @Provides
    @Singleton
    fun provideEventsApi(retrofit: Retrofit) : IEvents
    {
        return retrofit.create(IEvents::class.java)
    }
}