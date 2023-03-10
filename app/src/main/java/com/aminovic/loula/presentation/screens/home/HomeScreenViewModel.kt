package com.aminovic.loula.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.aminovic.loula.domain.repository.MusicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 17/12/2022.
//

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val musicRepository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnSelectTab -> {
                _state.value = _state.value.copy(
                    selectedCategory = event.category
                )
            }
        }
    }
}
