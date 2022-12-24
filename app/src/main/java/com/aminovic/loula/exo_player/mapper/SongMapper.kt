package com.aminovic.loula.exo_player.mapper

import android.net.Uri
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import com.aminovic.loula.data.modal.SongModel
import com.aminovic.loula.domain.data.Song
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_ALBUM_ID
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_ARTIST_ID
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_MEDIA_ID
import com.aminovic.loula.exo_player.util.ALBUM_ID
import com.aminovic.loula.exo_player.util.ARTIST_ID
import com.aminovic.loula.exo_player.util.buildPlayableMediaItem

internal fun SongModel.asMediaItem() = buildPlayableMediaItem(
    mediaId = id.toString(),
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri.toUri(),
    artworkUri = artworkUri.toUri(),
    title = title,
    artist = artist
)

internal fun Song.asMediaItem() = buildPlayableMediaItem(
    mediaId = id.toString(),
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri,
    artworkUri = artworkUri,
    title = title,
    artist = artist
)

internal fun Song.asSongModel() = SongModel(
    id = id,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri.toString(),
    artworkUri = artworkUri.toString(),
    title = title,
    artist = artist,
    album = album
)

internal fun MediaItem?.asSong() = Song(
    id = this?.mediaId?.toInt() ?: DEFAULT_MEDIA_ID,
    artistId = this?.mediaMetadata?.extras?.getLong(ARTIST_ID) ?: DEFAULT_ARTIST_ID,
    albumId = this?.mediaMetadata?.extras?.getLong(ALBUM_ID) ?: DEFAULT_ALBUM_ID,
    mediaUri = this?.requestMetadata?.mediaUri.orEmpty(),
    artworkUri = this?.mediaMetadata?.artworkUri.orEmpty(),
    title = this?.mediaMetadata?.title.orEmpty(),
    artist = this?.mediaMetadata?.artist.orEmpty(),
    album = this?.mediaMetadata?.albumTitle.orEmpty()
)


fun SongModel.asSong() = Song(
    id = id,
    artistId = artistId,
    albumId = albumId,
    mediaUri = mediaUri.toUri(),
    artworkUri = artworkUri.toUri(),
    title = title,
    artist = artist,
    album = album
)

private fun Uri?.orEmpty() = this ?: Uri.EMPTY
private fun CharSequence?.orEmpty() = (this ?: "").toString()
