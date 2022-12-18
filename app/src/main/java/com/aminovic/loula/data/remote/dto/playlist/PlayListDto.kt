package com.aminovic.loula.data.remote.dto.playlist

import com.aminovic.loula.data.remote.dto.user.UserDto
import com.squareup.moshi.Json

data class PlayListDto(
    @field:Json(name = "id") var id: Long? = null,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "public") var public: Boolean? = null,
    @field:Json(name = "nb_tracks") var nbTracks: Int? = null,
    @field:Json(name = "link") var link: String? = null,
    @field:Json(name = "picture") var picture: String? = null,
    @field:Json(name = "picture_small") var pictureSmall: String? = null,
    @field:Json(name = "picture_medium") var pictureMedium: String? = null,
    @field:Json(name = "picture_big") var pictureBig: String? = null,
    @field:Json(name = "picture_xl") var pictureXl: String? = null,
    @field:Json(name = "checksum") var checksum: String? = null,
    @field:Json(name = "tracklist") var tracklist: String? = null,
    @field:Json(name = "creation_date") var creationDate: String? = null,
    @field:Json(name = "md5_image") var md5Image: String? = null,
    @field:Json(name = "picture_type") var pictureType: String? = null,
    @field:Json(name = "user") var user: UserDto? = UserDto(),
    @field:Json(name = "type") var type: String? = null
)
