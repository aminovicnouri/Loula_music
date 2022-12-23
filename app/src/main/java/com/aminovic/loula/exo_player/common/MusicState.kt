package com.aminovic.loula.exo_player.common

import android.net.Uri
import com.aminovic.loula.domain.data.Song
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_ALBUM_ID
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_ARTIST_ID
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_DURATION_MS
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_MEDIA_ID

data class MusicState(
    val currentSong: Song = Song(
        mediaId = DEFAULT_MEDIA_ID,
        artistId = DEFAULT_ARTIST_ID,
        albumId = DEFAULT_ALBUM_ID,
        mediaUri = Uri.EMPTY,
        artworkUri = Uri.EMPTY,
        title = "",
        artist = "",
        album = ""
    ),
    val playbackState: PlaybackState = PlaybackState.IDLE,
    val playWhenReady: Boolean = false,
    val duration: Long = DEFAULT_DURATION_MS
)
