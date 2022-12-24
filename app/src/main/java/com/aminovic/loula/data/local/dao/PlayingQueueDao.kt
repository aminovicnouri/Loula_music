package com.aminovic.loula.data.local.dao

import androidx.room.*
import com.aminovic.loula.data.local.entities.PlayingQueueEntity
import com.aminovic.loula.domain.utils.Constants.Tables.PLAYING_QUEUE
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayingQueueDao {
    @Query("SELECT * FROM $PLAYING_QUEUE")
    fun getAll(): Flow<List<PlayingQueueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<PlayingQueueEntity>)

    @Query("DELETE FROM $PLAYING_QUEUE")
    suspend fun deleteAll()

    @Transaction
    suspend fun deleteAndInsertAll(entities: List<PlayingQueueEntity>) {
        deleteAll()
        insertAll(entities)
    }
}
