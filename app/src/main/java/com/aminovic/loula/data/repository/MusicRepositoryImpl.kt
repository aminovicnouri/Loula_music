package com.aminovic.loula.data.repository

import com.aminovic.loula.data.remote.MusicApi
import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.radio.RadiosDataDto
import com.aminovic.loula.domain.repository.MusicRepository

class MusicRepositoryImpl(
    private val musicApi: MusicApi
) : MusicRepository {
    override suspend fun getGenres(): GenreDataDto {
        return musicApi.getGenres()
    }

    override suspend fun getAlbum(id: Int): AlbumDto {
        return musicApi.getAlbum(id = id)
    }

    override suspend fun getArtist(id: Int): ArtistDto {
        return musicApi.getArtist(id)
    }

    override suspend fun getChart(id: Int): ChartDto {
        return musicApi.getChart(id = id)
    }

    override suspend fun getRadios(): RadiosDataDto {
        return musicApi.getRadios()
    }
}