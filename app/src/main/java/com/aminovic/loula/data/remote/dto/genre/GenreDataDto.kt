package com.aminovic.loula.data.remote.dto.genre

import com.squareup.moshi.Json

data class GenreDataDto(
    @field:Json(name = "data")
    val genres: List<GenreDto> = emptyList()
)
