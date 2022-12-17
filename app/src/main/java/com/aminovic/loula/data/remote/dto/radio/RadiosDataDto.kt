package com.aminovic.loula.data.remote.dto.radio

import com.squareup.moshi.Json

data class RadiosDataDto(
    @field:Json(name = "data") var data: List<RadioDto> = emptyList()
)
