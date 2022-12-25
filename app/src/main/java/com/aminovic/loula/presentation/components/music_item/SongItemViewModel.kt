package com.aminovic.loula.presentation.components.music_item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.domain.utils.MediaConstants.DEFAULT_POSITION_MS
import com.aminovic.loula.exo_player.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 19/12/2022.
//

@HiltViewModel
class SongItemViewModel @Inject constructor(
    musicServiceConnection: MusicServiceConnection,
) : ViewModel() {
    val musicState = musicServiceConnection.musicState
    val currentPosition = musicServiceConnection.currentPosition.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = DEFAULT_POSITION_MS,
    )
}
