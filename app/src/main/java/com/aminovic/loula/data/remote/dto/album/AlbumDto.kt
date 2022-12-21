package com.aminovic.loula.data.remote.dto.album

import com.aminovic.loula.data.remote.dto.ContributorDto
import com.aminovic.loula.data.remote.dto.artist.ArtistDto
import com.aminovic.loula.data.remote.dto.genre.GenreDataDto
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.squareup.moshi.Json

data class AlbumDto(
    @field:Json(name = "id")
    var id: Int? = null,
    @field:Json(name = "title")
    var title: String? = null,
    @field:Json(name = "upc")
    var upc: String? = null,
    @field:Json(name = "link")
    var link: String? = null,
    @field:Json(name = "share")
    var share: String? = null,
    @field:Json(name = "cover")
    var cover: String? = null,
    @field:Json(name = "cover_small")
    var coverSmall: String? = null,
    @field:Json(name = "cover_medium")
    var coverMedium: String? = null,
    @field:Json(name = "cover_big")
    var coverBig: String? = null,
    @field:Json(name = "cover_xl")
    var coverXl: String? = null,
    @field:Json(name = "md5_image")
    var md5Image: String? = null,
    @field:Json(name = "genre_id")
    var genreId: Int? = null,
    @field:Json(name = "genres")
    var genres: GenreDataDto? = GenreDataDto(),
    @field:Json(name = "label")
    var label: String? = null,
    @field:Json(name = "nb_tracks")
    var nbTracks: Int? = null,
    @field:Json(name = "duration")
    var duration: Int? = null,
    @field:Json(name = "fans")
    var fans: Int? = null,
    @field:Json(name = "release_date")
    var releaseDate: String? = null,
    @field:Json(name = "record_type")
    var recordType: String? = null,
    @field:Json(name = "available")
    var available: Boolean? = null,
    @field:Json(name = "tracklist")
    var tracklist: String? = null,
    @field:Json(name = "explicit_lyrics")
    var explicitLyrics: Boolean? = null,
    @field:Json(name = "explicit_content_lyrics")
    var explicitContentLyrics: Int? = null,
    @field:Json(name = "explicit_content_cover")
    var explicitContentCover: Int? = null,
    @field:Json(name = "contributors")
    var contributors: List<ContributorDto> = emptyList(),
    @field:Json(name = "artist")
    var artist: ArtistDto? = ArtistDto(),
    @field:Json(name = "type")
    var type: String? = null,
    @field:Json(name = "tracks")
    var tracks: TrackDataDto? = TrackDataDto()
)
