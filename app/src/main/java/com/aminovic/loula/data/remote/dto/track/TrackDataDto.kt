package com.aminovic.loula.data.remote.dto.track

import com.squareup.moshi.Json

data class TrackDataDto(
    @field:Json(name = "data") var data: List<TrackDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
