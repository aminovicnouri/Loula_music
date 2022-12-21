package com.aminovic.loula.presentation.screens.home.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 18/12/2022.
//

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private var repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DiscoverViewState())

    val state: StateFlow<DiscoverViewState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getChart(0).let { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            chart = result.data,
                            selectedAlbum = result.data?.albums?.albums?.first(),
                            isLoading = false,
                            errorMessage = null
                        )
                        state.value.selectedAlbum?.let { album ->
                            refreshAlbumTracks(album.id!!)
                        }
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            chart = null,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: DiscoverEvents) {
        when (event) {
            is DiscoverEvents.OnSelectGenre -> {
                _state.value = state.value.copy(
                    //  selectedGenre = event.genre,
                )
            }
            is DiscoverEvents.OnSelectAlbum -> {
                refreshAlbumTracks(event.album.id!!)
            }
            is DiscoverEvents.OnSelectArtist -> {
                _state.value = state.value.copy(
                    selectedArtist = event.artist
                )
            }
        }
    }

    private fun refreshAlbumTracks(album: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getAlbum(album).let { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            selectedAlbum = result.data,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            chart = null,
                            errorMessage = result.message
                        )
                    }
                }
            }
        }

    }
}