package com.aminovic.loula.data.remote.dto.album

import com.aminovic.loula.data.remote.dto.album.AlbumDto
import com.squareup.moshi.Json

data class AlbumsDataDto(
    @field:Json(name = "data") var data: List<AlbumDto> = emptyList(),
    @field:Json(name = "total") var total: Int? = null
)
