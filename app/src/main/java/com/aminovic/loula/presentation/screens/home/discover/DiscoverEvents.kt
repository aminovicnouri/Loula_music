package com.aminovic.loula.presentation.screens.home.discover

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.genre.GenreDto

sealed class DiscoverEvents {
    data class OnSelectGenre(val genre: GenreDto) : DiscoverEvents()
    data class OnSelectAlbum(val album: AlbumDto) : DiscoverEvents()
    data class OnSelectArtist(val artist: ArtistDto) : DiscoverEvents()
}
