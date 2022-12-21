package com.aminovic.loula.data.remote.dto.radio

import com.squareup.moshi.Json

data class RadioDto(
    @field:Json(name = "id") var id: Int? = null,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "picture") var picture: String? = null,
    @field:Json(name = "picture_small") var pictureSmall: String? = null,
    @field:Json(name = "picture_medium") var pictureMedium: String? = null,
    @field:Json(name = "picture_big") var pictureBig: String? = null,
    @field:Json(name = "picture_xl") var pictureXl: String? = null,
    @field:Json(name = "tracklist") var trackList: String? = null,
    @field:Json(name = "md5_image") var md5Image: String? = null,
    @field:Json(name = "type") var type: String? = null
)
