package com.aminovic.loula.data.remote.dto.podcast

import com.squareup.moshi.Json

data class PodcastDto(
    @field:Json(name = "id") var id: Int? = null,
    @field:Json(name = "title") var title: String? = null,
    @field:Json(name = "description") var description: String? = null,
    @field:Json(name = "available") var available: Boolean? = null,
    @field:Json(name = "fans") var fans: Int? = null,
    @field:Json(name = "link") var link: String? = null,
    @field:Json(name = "share") var share: String? = null,
    @field:Json(name = "picture") var picture: String? = null,
    @field:Json(name = "picture_small") var pictureSmall: String? = null,
    @field:Json(name = "picture_medium") var pictureMedium: String? = null,
    @field:Json(name = "picture_big") var pictureBig: String? = null,
    @field:Json(name = "picture_xl") var pictureXl: String? = null,
    @field:Json(name = "type") var type: String? = null
)
