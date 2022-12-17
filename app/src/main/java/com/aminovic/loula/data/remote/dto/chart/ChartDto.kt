package com.aminovic.loula.data.remote.dto.chart

import com.aminovic.loula.data.remote.dto.album.AlbumsDataDto
import com.aminovic.loula.data.remote.dto.artist.ArtistsDataDto
import com.aminovic.loula.data.remote.dto.playlist.PlayListsDataDto
import com.aminovic.loula.data.remote.dto.podcast.PodcastsDataDto
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.squareup.moshi.Json

data class ChartDto(
    @field:Json(name = "tracks") var tracks: TrackDataDto? = TrackDataDto(),
    @field:Json(name = "albums") var albums: AlbumsDataDto? = AlbumsDataDto(),
    @field:Json(name = "artists") var artists: ArtistsDataDto? = ArtistsDataDto(),
    @field:Json(name = "playlists") var playlists: PlayListsDataDto? = PlayListsDataDto(),
    @field:Json(name = "podcasts") var podcasts: PodcastsDataDto? = PodcastsDataDto()
)
