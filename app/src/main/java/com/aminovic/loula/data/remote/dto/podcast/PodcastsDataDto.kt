package com.aminovic.loula.data.remote.dto.podcast

import com.squareup.moshi.Json

data class PodcastsDataDto(
    @field:Json(name = "data") var data: List<PodcastDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
