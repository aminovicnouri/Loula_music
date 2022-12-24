package com.aminovic.loula.exo_player.util

import androidx.media3.common.C
import androidx.media3.common.Player
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_DURATION_MS
import com.aminovic.loula.exo_player.common.PlaybackState
import com.aminovic.loula.exo_player.util.Constants.INVALID_PLAYBACK_STATE_ERROR_MESSAGE

internal fun Int.asPlaybackState() = when (this) {
    Player.STATE_IDLE -> PlaybackState.IDLE
    Player.STATE_BUFFERING -> PlaybackState.BUFFERING
    Player.STATE_READY -> PlaybackState.READY
    Player.STATE_ENDED -> PlaybackState.ENDED
    else -> error(INVALID_PLAYBACK_STATE_ERROR_MESSAGE)
}

internal fun Long.orDefaultTimestamp() = takeIf { it != C.TIME_UNSET } ?: DEFAULT_DURATION_MS
