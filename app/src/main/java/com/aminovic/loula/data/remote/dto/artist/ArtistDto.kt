package com.aminovic.loula.data.remote.dto.artist

import com.squareup.moshi.Json

data class ArtistDto(
    @field:Json(name = "id") var id: String? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "link") var link: String? = null,
    @field:Json(name = "share") var share: String? = null,
    @field:Json(name = "picture") var picture: String? = null,
    @field:Json(name = "picture_small") var pictureSmall: String? = null,
    @field:Json(name = "picture_medium") var pictureMedium: String? = null,
    @field:Json(name = "picture_big") var pictureBig: String? = null,
    @field:Json(name = "picture_xl") var pictureXl: String? = null,
    @field:Json(name = "nb_album") var nbAlbum: Int? = null,
    @field:Json(name = "nb_fan") var nbFan: Int? = null,
    @field:Json(name = "radio") var radio: Boolean? = null,
    @field:Json(name = "tracklist") var tracklist: String? = null,
    @field:Json(name = "type") var type: String? = null
)
