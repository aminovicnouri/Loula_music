package com.aminovic.loula.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.aminovic.loula.data.utils.PaginatorImpl
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.use_case.PlayPauseListUseCase
import com.aminovic.loula.domain.utils.Resource
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
    private val playPauseListUseCase: PlayPauseListUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())

    val state: StateFlow<ProfileState>
        get() = _state

    private lateinit var paginator: PaginatorImpl<String?, TrackDataDto>

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.GetArtist -> getArtist(event.artistId)
            is ProfileEvent.InitiatePaginator -> initiatePaginator(event.query)
            is ProfileEvent.LoadNextItems -> loadNextItems()
            is ProfileEvent.PlaySound -> {
                playPauseListUseCase(
                    isRunning = event.isRunning,
                    playWhenReady = event.playWhenReady,
                    startIndex = event.idx,
                    list = state.value.tracks
                )
            }
        }
    }

    private fun getArtist(id: Int) {
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

    private fun initiatePaginator(query: String) {
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

    private fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }
}