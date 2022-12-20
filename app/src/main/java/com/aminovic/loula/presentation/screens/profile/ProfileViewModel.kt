package com.aminovic.loula.presentation.screens.profile

import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.loula.data.remote.dto.track.TrackDataDto
import com.aminovic.loula.data.utils.PaginatorImpl
import com.aminovic.loula.domain.repository.MusicRepository
import com.aminovic.loula.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


//
// Created by Mohamed El Amine Nouri on 19/12/2022.
//

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: MusicRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())

    val state: StateFlow<ProfileState>
        get() = _state

    private lateinit var paginator: PaginatorImpl<String?, TrackDataDto>


    private var mediaPlayer: MediaPlayer = MediaPlayer()
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

    fun playSound(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            mediaPlayer.stop()
            mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                mediaPlayer.setDataSource(url)
                mediaPlayer.prepare()
                mediaPlayer.start()
            } catch (e: Exception) {
                Log.d("hhhhhhhh", "${e.message}")
            }
        }
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