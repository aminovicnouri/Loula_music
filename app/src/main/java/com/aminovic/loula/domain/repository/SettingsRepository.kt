package com.aminovic.loula.domain.repository

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getPlayingQueueIndex(): Flow<Int>
    suspend fun setPlayingQueueIndex(index: Int)
}
