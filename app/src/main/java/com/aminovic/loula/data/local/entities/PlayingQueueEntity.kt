package com.aminovic.loula.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aminovic.loula.domain.utils.Constants

@Entity(tableName = Constants.Tables.PLAYING_QUEUE)
data class PlayingQueueEntity(
    @PrimaryKey
    @ColumnInfo(name = Constants.Fields.ID)
    val id: Int,

    @ColumnInfo(name = Constants.Fields.ARTIST_ID)
    val artistId: Long,

    @ColumnInfo(name = Constants.Fields.ALBUM_ID)
    val albumId: Long,

    @ColumnInfo(name = Constants.Fields.MEDIA_URI)
    val mediaUri: String,

    @ColumnInfo(name = Constants.Fields.ARTWORK_URI)
    val artworkUri: String,

    @ColumnInfo(name = Constants.Fields.TITLE)
    val title: String,

    @ColumnInfo(name = Constants.Fields.ARTIST)
    val artist: String,

    @ColumnInfo(name = Constants.Fields.ALBUM)
    val album: String
)
