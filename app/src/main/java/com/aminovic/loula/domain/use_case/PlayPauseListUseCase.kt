package com.aminovic.loula.domain.use_case

import com.aminovic.loula.data.mappers.asSong
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.domain.utils.MediaConstants
import com.aminovic.loula.exo_player.MusicServiceConnection
import javax.inject.Inject

class PlayPauseListUseCase @Inject constructor(private val musicServiceConnection: MusicServiceConnection) {
    operator fun invoke(
        list: List<TrackDto>,
        startIndex: Int = MediaConstants.DEFAULT_INDEX,
        isRunning: Boolean = false,
        playWhenReady: Boolean = false
    ) {
        if (isRunning) {
            if (!playWhenReady) {
                musicServiceConnection.play()
            } else {
                musicServiceConnection.pause()
            }
        } else {
            musicServiceConnection.playSongs(
                songs = list.map { it.asSong() },
                startIndex = startIndex
            )
        }
    }
}


