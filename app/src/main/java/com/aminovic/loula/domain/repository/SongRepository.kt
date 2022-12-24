package com.aminovic.loula.domain.repository

import com.aminovic.loula.data.modal.SongModel
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getPlayingQueue(): Flow<List<SongModel>>
    suspend fun setPlayingQueue(songs: List<SongModel>)
}
