package com.aminovic.loula.data.repository

import com.aminovic.loula.data.local.MusicDatabase
import com.aminovic.loula.data.local.entities.PlayingQueueEntity
import com.aminovic.loula.data.mappers.asPlayingQueueEntity
import com.aminovic.loula.data.mappers.asSongModel
import com.aminovic.loula.data.mappers.listMap
import com.aminovic.loula.data.modal.SongModel
import com.aminovic.loula.domain.repository.SongRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SongRepositoryImpl @Inject constructor(
    private val db: MusicDatabase
) : SongRepository {
    override fun getPlayingQueue(): Flow<List<SongModel>> =
        db.playingQueueDao.getAll().listMap(PlayingQueueEntity::asSongModel)

    override suspend fun setPlayingQueue(songs: List<SongModel>) =
        db.playingQueueDao.deleteAndInsertAll(entities = songs.map(SongModel::asPlayingQueueEntity))
}
