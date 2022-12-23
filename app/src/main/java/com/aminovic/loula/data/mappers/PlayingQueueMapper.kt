package com.aminovic.loula.data.mappers

import com.aminovic.loula.data.local.entities.PlayingQueueEntity
import com.aminovic.loula.data.modal.SongModel

internal fun SongModel.asPlayingQueueEntity() = PlayingQueueEntity(
    mediaId = mediaId,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri,
    artworkUri = artworkUri,
    title = title,
    artist = artist,
    album = album
)

internal fun PlayingQueueEntity.asSongModel() = SongModel(
    mediaId = mediaId,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri,
    artworkUri = artworkUri,
    title = title,
    artist = artist,
    album = album
)
