package com.aminovic.loula.data.remote

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.chart.ChartDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.radio.RadiosDataDto
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MusicApi {
    @GET("genre")
    suspend fun getGenres(): GenreDataDto

    @GET("album/{id}")
    suspend fun getAlbum(@Path("id") id: Int): AlbumDto

    @GET("artist/{id}")
    suspend fun getArtist(@Path("id") id: Int): ArtistDto

    @GET("artist/{id}/top")
    suspend fun getArtistTracks(
        @Path("id") id: Int,
        @Query("limit") limit: Int = 50,
        @Query("index") index: Int = 0
    ): TrackDataDto


    @GET("chart/{id}")
    suspend fun getChart(@Path("id") id: Int): ChartDto

    @GET("radio")
    suspend fun getRadios(): RadiosDataDto
}