package com.aminovic.loula.data.remote.dto.artist

import com.squareup.moshi.Json

data class ArtistsDataDto(
    @field:Json(name = "data"  ) var data  : List<ArtistDto> = emptyList(),
    @field:Json(name = "total" ) var total : Int?            = null
)
