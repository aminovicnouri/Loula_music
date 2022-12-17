package com.aminovic.loula.di

import com.aminovic.loula.data.remote.MusicApi
import com.aminovic.loula.data.repository.MusicRepositoryImpl
import com.aminovic.loula.domain.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMusicApi(): MusicApi {
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMusicRepository(api: MusicApi): MusicRepository {
        return MusicRepositoryImpl(musicApi = api)
    }
}