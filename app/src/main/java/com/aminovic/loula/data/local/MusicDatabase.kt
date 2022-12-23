package com.aminovic.loula.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aminovic.loula.data.local.dao.PlayingQueueDao
import com.aminovic.loula.data.local.entities.PlayingQueueEntity

@Database(
    entities = [PlayingQueueEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract val playingQueueDao: PlayingQueueDao
}