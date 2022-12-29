package com.aminovic.loula.domain.use_case

import com.aminovic.loula.data.mappers.asSong
import com.aminovic.loula.data.remote.dto.track.TrackDto
import com.aminovic.loula.domain.utils.MediaConstants
import com.aminovic.loula.exo_player.MusicServiceConnection
import javax.inject.Inject

class PlayListUseCase @Inject constructor(private val musicServiceConnection: MusicServiceConnection) {
    operator fun invoke(list: List<TrackDto>, startIndex: Int = MediaConstants.DEFAULT_INDEX) =
        musicServiceConnection.playSongs(
            songs = list.map { it.asSong() },
            startIndex = startIndex
        )
}
