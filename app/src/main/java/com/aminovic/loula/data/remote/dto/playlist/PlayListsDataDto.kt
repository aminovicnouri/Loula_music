package com.aminovic.loula.data.remote.dto.playlist

import com.squareup.moshi.Json

data class PlayListsDataDto(
    @field:Json(name = "data") var data: List<PlayListDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
