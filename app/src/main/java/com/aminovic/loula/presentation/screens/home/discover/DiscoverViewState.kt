package com.aminovic.loula.presentation.screens.home.discover

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto

data class DiscoverViewState(
    val chart: ChartDto? = null,
    val selectedAlbum: AlbumDto? = null,
    val selectedArtist: ArtistDto? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
