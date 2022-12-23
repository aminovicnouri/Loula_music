package com.aminovic.loula.data.mappers

import androidx.core.net.toUri
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.domain.data.Song
import com.aminovic.loula.domain.utils.MediaConstants

fun TrackDto.asSong() = Song(
    mediaId = this.id.toString() ?: MediaConstants.DEFAULT_MEDIA_ID,
    artistId = this.artist?.id?.toLong() ?: MediaConstants.DEFAULT_ARTIST_ID,
    albumId = this.album?.id?.toLong() ?: MediaConstants.DEFAULT_ALBUM_ID,
    mediaUri = this.preview!!.toUri(),
    artworkUri = this.album!!.coverBig!!.toUri(),
    title = this.title.orEmpty(),
    artist = this.artist?.name.orEmpty(),
    album = this.album?.title.orEmpty()
)
