package com.aminovic.loula.presentation.screens.player

sealed class PlayerEvent {
    object Play: PlayerEvent()
    object Pause: PlayerEvent()
    object SkipNext: PlayerEvent()
    object SkipPrevious: PlayerEvent()
}
