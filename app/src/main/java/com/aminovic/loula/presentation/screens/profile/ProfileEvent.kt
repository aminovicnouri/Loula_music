package com.aminovic.loula.presentation.screens.profile

sealed class ProfileEvent {
    data class GetArtist(val artistId: Int) : ProfileEvent()
    data class InitiatePaginator(val query: String) : ProfileEvent()
    data class PlaySound(val isRunning: Boolean, val idx: Int) : ProfileEvent()
    object LoadNextItems : ProfileEvent()
}
