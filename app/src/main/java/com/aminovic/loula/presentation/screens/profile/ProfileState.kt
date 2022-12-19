package com.aminovic.loula.presentation.screens.profile

import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.track.TrackDto

data class ProfileState(
    val artist: ArtistDto? = null,
    val tracks: List<TrackDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
