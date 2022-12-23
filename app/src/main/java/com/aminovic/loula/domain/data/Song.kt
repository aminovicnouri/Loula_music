package com.aminovic.loula.domain.data

import android.net.Uri

data class Song(
    val mediaId: String,
    val artistId: Long,
    val albumId: Long,
    val mediaUri: Uri,
    val artworkUri: Uri,
    val title: String,
    val artist: String,
    val album: String
)
