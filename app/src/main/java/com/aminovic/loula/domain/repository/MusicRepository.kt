package com.aminovic.loula.domain.repository

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.radio.RadiosDataDto
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.aminovic.loula.domain.utils.Resource

interface MusicRepository {
    suspend fun getGenres(): Resource<GenreDataDto>

    suspend fun getAlbum(id: Int): Resource<AlbumDto>

    suspend fun getArtist(id: Int): Resource<ArtistDto>

    suspend fun getArtistTrack(
        id: Int,
        limit: Int = 50,
        index: Int = 0
    ): Resource<TrackDataDto>

    suspend fun getArtistTracksPaging(
        query: String
    ): Resource<TrackDataDto>

    suspend fun getChart(id: Int): Resource<ChartDto>

    suspend fun getRadios(): Resource<RadiosDataDto>
}