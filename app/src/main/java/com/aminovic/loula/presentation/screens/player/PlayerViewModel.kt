package com.aminovic.loula.presentation.screens.player

import androidx.lifecycle.ViewModel
import com.aminovic.loula.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 20/12/2022.
//

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())

    val state: StateFlow<PlayerState>
        get() = _state

}