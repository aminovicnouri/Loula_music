package com.aminovic.loula.data.remote.dto.track

import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.ContributorDto
import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.squareup.moshi.Json

data class TrackDto(
    @field:Json(name = "id") var id: String? = null,
    @field:Json(name = "readable") var readable: Boolean? = null,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "title_short") var titleShort: String? = null,
    @field:Json(name = "title_version") var titleVersion: String? = null,
    @field:Json(name = "isrc") var isrc: String? = null,
    @field:Json(name = "link") var link: String? = null,
    @field:Json(name = "share") var share: String? = null,
    @field:Json(name = "duration") var duration: String? = null,
    @field:Json(name = "track_position") var trackPosition: Int? = null,
    @field:Json(name = "disk_number") var diskNumber: Int? = null,
    @field:Json(name = "rank") var rank: String? = null,
    @field:Json(name = "release_date") var releaseDate: String? = null,
    @field:Json(name = "explicit_lyrics") var explicitLyrics: Boolean? = null,
    @field:Json(name = "explicit_content_lyrics") var explicitContentLyrics: Int? = null,
    @field:Json(name = "explicit_content_cover") var explicitContentCover: Int? = null,
    @field:Json(name = "preview") var preview: String? = null,
    @field:Json(name = "bpm") var bpm: Double? = null,
    @field:Json(name = "gain") var gain: Double? = null,
    @field:Json(name = "available_countries") var availableCountries: List<String> = emptyList(),
    @field:Json(name = "contributors") var contributors: List<ContributorDto> = emptyList(),
    @field:Json(name = "md5_image") var md5Image: String? = null,
    @field:Json(name = "artist") var artist: ArtistDto? = ArtistDto(),
    @field:Json(name = "album") var album: AlbumDto? = AlbumDto(),
    @field:Json(name = "type") var type: String? = null
)
