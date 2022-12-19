package com.aminovic.loula.data.repository

import com.aminovic.loula.data.remote.MusicApi
import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.radio.RadiosDataDto
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.utils.Resource

class MusicRepositoryImpl(
    private val musicApi: MusicApi
) : MusicRepository {
    override suspend fun getGenres(): Resource<GenreDataDto> {
        return try {
            Resource.Success(data = musicApi.getGenres())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun getAlbum(id: Int): Resource<AlbumDto> {
        return try {
            Resource.Success(data = musicApi.getAlbum(id = id))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun getArtist(id: Int): Resource<ArtistDto> {
        return try {
            Resource.Success(data = musicApi.getArtist(id))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun getArtistTrack(
        id: Int,
        limit: Int,
        index: Int
    ): Resource<TrackDataDto> {
        return try {
            Resource.Success(data = musicApi.getArtistTracks(id, limit, index))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun getChart(id: Int): Resource<ChartDto> {
        return try {
            Resource.Success(data = musicApi.getChart(id = id))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }

    override suspend fun getRadios(): Resource<RadiosDataDto> {
        return try {
            Resource.Success(data = musicApi.getRadios())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = e.message ?: "Unknown error")
        }
    }
}