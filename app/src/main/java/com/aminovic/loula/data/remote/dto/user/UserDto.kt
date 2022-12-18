package com.aminovic.loula.data.remote.dto.user

import com.squareup.moshi.Json

data class UserDto(
    @field:Json(name = "id") var id: Long? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "tracklist") var trackList: String? = null,
    @field:Json(name = "type") var type: String? = null
)
