package com.aminovic.loula.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.data.mappers.asSong
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.aminovic.loula.data.utils.PaginatorImpl
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.utils.MediaConstants
import com.aminovic.loula.domain.utils.Resource
import com.aminovic.loula.exo_player.MusicServiceConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 19/12/2022.
//

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MusicRepository,
    private val musicServiceConnection: MusicServiceConnection,
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())

    val state: StateFlow<ProfileState>
        get() = _state

    private lateinit var paginator: PaginatorImpl<String?, TrackDataDto>


    fun play(startIndex: Int = MediaConstants.DEFAULT_INDEX) =
        musicServiceConnection.playSongs(
            songs = _state.value.tracks.map { it.asSong() },
            startIndex = startIndex
        )

    fun shuffle() =
        musicServiceConnection.shuffleSongs(songs = _state.value.tracks.map { it.asSong() })

    fun getArtist(id: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getArtist(id).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            errorMessage = result.message
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            artist = result.data
                        )
                    }
                }
            }
        }
    }


    private fun getArtistTracks(id: Int) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            repository.getArtistTrack(id).let { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            errorMessage = null,
                            tracks = result.data?.data ?: emptyList(),
                            nextPage = result.data?.next
                        )
                    }
                }
            }
        }
    }

    fun playSound(index: Int) {
        musicServiceConnection.playSongs(
            songs = _state.value.tracks.map { it.asSong() },
            startIndex = index
        )
    }

    fun initiatePaginator(query: String) {
        _state.value = state.value.copy(
            nextPage = query
        )
        paginator = PaginatorImpl<String?, TrackDataDto>(
            initialKey = query,
            onLoadUpdated = {
                _state.value = state.value.copy(
                    isLoading = it
                )
            },
            onRequest = { nextIndex ->
                repository.getArtistTracksPaging(query = nextIndex!!)
            },
            getNextKey = {
                _state.value = _state.value.copy(
                    nextPage = it.next
                )
                it.next
            },
            onError = {
                _state.value = _state.value.copy(
                    errorMessage = it?.message
                )
            },
            onSuccess = { result ->
                _state.value = _state.value.copy(
                    tracks = state.value.tracks + result.data,
                    nextPage = state.value.nextPage,
                )
            }
        )
        loadNextItems()
    }

    fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}