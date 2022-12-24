package com.aminovic.loula.data.repository

import com.aminovic.loula.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val preferencesDataStoreDataSource: PreferencesDataStoreDataSource,
) : SettingsRepository {

    override fun getPlayingQueueIndex(): Flow<Int> =
        preferencesDataStoreDataSource.getPlayingQueueIndex()

    override suspend fun setPlayingQueueIndex(index: Int) {
        preferencesDataStoreDataSource.setPlayingQueueIndex(index)
    }
}
