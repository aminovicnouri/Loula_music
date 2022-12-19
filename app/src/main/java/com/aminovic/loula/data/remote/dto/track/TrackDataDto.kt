package com.aminovic.loula.data.remote.dto.track

import com.squareup.moshi.Json

data class TrackDataDto(
    @field:Json(name = "data") var data: List<TrackDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null,
    @field:Json(name = "next") var next: String? = null,
    @field:Json(name = "prev") var prev: String? = null,
)
