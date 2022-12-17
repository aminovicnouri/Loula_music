package com.aminovic.loula.domain.repository

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.radio.RadiosDataDto

interface MusicRepository {
    suspend fun getGenres(): GenreDataDto

    suspend fun getAlbum(id: Int): AlbumDto

    suspend fun getArtist(id: Int): ArtistDto

    suspend fun getChart(id: Int): ChartDto

    suspend fun getRadios(): RadiosDataDto
}