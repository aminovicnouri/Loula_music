package com.aminovic.loula.presentation.screens

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screens(val route: String) {
    object Home : Screens(route = "home")
    object Profile :
        Screens(route = "profile/{artistId}/{artistImageUrl}/{artistName}/{artistTrackList}") {
        fun createRoute(
            artistId: Int,
            artistImageUrl: String,
            artistName: String,
            artistTrackList: String,
        ): String {
            val encodedImageUrl =
                URLEncoder.encode(artistImageUrl, StandardCharsets.UTF_8.toString())
            val encodedTrackListUrl =
                URLEncoder.encode(artistTrackList, StandardCharsets.UTF_8.toString())
            return "profile/$artistId/${encodedImageUrl}/$artistName/$encodedTrackListUrl"
        }
    }
}
