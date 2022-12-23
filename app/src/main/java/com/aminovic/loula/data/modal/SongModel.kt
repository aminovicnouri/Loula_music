package com.aminovic.loula.data.modal

data class SongModel(
    val mediaId: String,
    val artistId: Long,
    val albumId: Long,
    val mediaUri: String,
    val artworkUri: String,
    val title: String,
    val artist: String,
    val album: String
)
