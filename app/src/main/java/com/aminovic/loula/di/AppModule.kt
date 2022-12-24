package com.aminovic.loula.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.aminovic.loula.data.local.MusicDatabase
import com.aminovic.loula.data.remote.MusicApi
import com.aminovic.loula.data.repository.MusicRepositoryImpl
import com.aminovic.loula.data.repository.PreferencesDataStoreDataSource
import com.aminovic.loula.data.repository.SettingsRepositoryImpl
import com.aminovic.loula.data.repository.SongRepositoryImpl
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.repository.SettingsRepository
import com.aminovic.loula.domain.repository.SongRepository
import com.aminovic.loula.domain.utils.Constants.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideMusicDatabase(app: Application): MusicDatabase {
        return Room.databaseBuilder(
            app,
            MusicDatabase::class.java,
            "music_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMusicDatabaseRepository(
        db: MusicDatabase
    ): SongRepository {
        return SongRepositoryImpl(db)
    }


    @Provides
    @Singleton
    fun providePreferencesDataStoreDataSource(
        dataStore: DataStore<Preferences>
    ): PreferencesDataStoreDataSource {
        return PreferencesDataStoreDataSource(dataStore)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        preferencesDataStoreDataSource: PreferencesDataStoreDataSource
    ): SettingsRepository {
        return SettingsRepositoryImpl(preferencesDataStoreDataSource)
    }

    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context) =
        context.preferencesDataStore
}