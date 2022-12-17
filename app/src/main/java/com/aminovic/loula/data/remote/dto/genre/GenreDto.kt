package com.aminovic.loula.data.remote.dto.genre
import com.squareup.moshi.Json


data class GenreDto(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "name")
    var name: String? = null,
    @field:Json(name = "picture")
    var picture: String? = null,
    @field:Json(name = "picture_small")
    var pictureSmall : String? = null,
    @field:Json(name = "picture_medium") 
    var pictureMedium: String? = null,
     @field:Json(name = "picture_big") 
    var pictureBig: String? = null,
     @field:Json(name = "picture_xl") 
    var pictureXl: String? = null,
     @field:Json(name = "type") 
    var type: String? = null
)
